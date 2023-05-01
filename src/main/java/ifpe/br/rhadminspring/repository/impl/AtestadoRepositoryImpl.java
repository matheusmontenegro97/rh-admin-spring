package ifpe.br.rhadminspring.repository.impl;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import ifpe.br.rhadminspring.config.AtestadoCodec;
import ifpe.br.rhadminspring.exceptions.FuncionarioNotFoundException;
import ifpe.br.rhadminspring.model.Atestado;
import ifpe.br.rhadminspring.repository.AtestadoRepository;
import ifpe.br.rhadminspring.repository.FuncionarioRepository;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Component
public class AtestadoRepositoryImpl implements AtestadoRepository {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public MongoCollection<Atestado> getCollection() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(
                        PojoCodecProvider.builder().automatic(true)
                                .register(AtestadoCodec.class)
                                .build()
                )
        );
        return mongoClient.getDatabase("rhadmin-spring").getCollection("atestado", Atestado.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

    private GridFSBucket getGridFSBuckets() {
        return GridFSBuckets.create(mongoClient.getDatabase("rhadmin-spring"));
    }

    public Atestado saveAtestado(Atestado atestado) throws Exception {

        Optional.of(funcionarioRepository.findFuncionarioById(atestado.getCodigoFuncionario()))
                .orElseThrow(() ->
                        new FuncionarioNotFoundException(String.format("Funcionario com id: %s não encontrado", atestado.getCodigoFuncionario())));

        atestado.setCodigoAtestado(UUID.randomUUID().toString());

        File file = new File(atestado.getAtestado());
        InputStream targetStream = new FileInputStream(file);
        getGridFSBuckets().uploadFromStream(atestado.getCodigoAtestado(), targetStream);

        getCollection().insertOne(atestado);

        return atestado;
    }
}