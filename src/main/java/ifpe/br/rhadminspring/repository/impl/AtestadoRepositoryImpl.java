package ifpe.br.rhadminspring.repository.impl;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import ifpe.br.rhadminspring.exceptions.FuncionarioNotFoundException;
import ifpe.br.rhadminspring.model.Atestado;
import ifpe.br.rhadminspring.repository.AtestadoRepository;
import ifpe.br.rhadminspring.repository.FuncionarioRepository;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class AtestadoRepositoryImpl implements AtestadoRepository {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private MongoCollection<Document> getCollection() {
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return mongoClient.getDatabase("rhadmin-spring").getCollection("rhadmin-spring").withCodecRegistry(pojoCodecRegistry);
    }

    private GridFSBucket getGridFSBuckets() {
        return GridFSBuckets.create(mongoClient.getDatabase("rhadmin-spring"));
    }

    public Atestado saveAtestado(MultipartFile multipartFile, String codigoFuncionario) throws Exception {

        Atestado atestado = new Atestado();
        File file = convertMultiPartToFile(multipartFile);
        atestado.setCodigoAtestado(UUID.randomUUID().toString());
        atestado.setCodigoFuncionario(codigoFuncionario);
        atestado.setAtestado(file);

        Optional.of(funcionarioRepository.findFuncionarioById(atestado.getCodigoFuncionario()))
                .orElseThrow(() -> new FuncionarioNotFoundException(String.format("Funcionario com id: %s não encontrado", atestado.getCodigoFuncionario())));

        InputStream targetStream = new FileInputStream(file);
        getGridFSBuckets().uploadFromStream(atestado.getCodigoAtestado(), targetStream);

        Document document = new Document()
                .append("codigoAtestado", atestado.getCodigoAtestado())
                .append("codigoFuncionario", atestado.getCodigoFuncionario());

        getCollection().insertOne(document);

        return atestado;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

}