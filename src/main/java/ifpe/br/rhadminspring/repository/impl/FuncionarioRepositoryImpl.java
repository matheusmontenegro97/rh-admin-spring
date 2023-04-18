package ifpe.br.rhadminspring.repository.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import ifpe.br.rhadminspring.model.Funcionario;
import ifpe.br.rhadminspring.repository.FuncionarioRepository;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

@Component
public class FuncionarioRepositoryImpl implements FuncionarioRepository {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private ObjectMapper om;


    private MongoCollection<Document> getCollection() {
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return mongoClient.getDatabase("rhadmin-spring").getCollection("rhadmin-spring").withCodecRegistry(pojoCodecRegistry);
    }


    @Override
    public Funcionario saveFuncionario(Funcionario funcionario) {

        funcionario.setCodigoFuncionario(UUID.randomUUID().toString());

        Document document = new Document()
                .append("codigoFuncionario", funcionario.getCodigoFuncionario())
                .append("nome", funcionario.getNome())
                .append("nomeSocial", funcionario.getNomeSocial())
                .append("dataNascimento", funcionario.getDataNascimento().toString())
                .append("cargo", funcionario.getCargo())
                .append("cpf", funcionario.getCpf())
                .append("rg", funcionario.getRg())
                .append("endereco", funcionario.getEndereco())
                .append("email", funcionario.getEmail());

        getCollection().insertOne(document);

        return funcionario;
    }

    @Override
    public Funcionario updateFuncionario(String codigoFuncionario, Funcionario funcionario) {
        Document doc = findDocumentById(codigoFuncionario);

        Document document = doc;
        document.put("nome", funcionario.getNome());
        document.put("nomeSocial", funcionario.getNomeSocial());
        document.put("dataNascimento", funcionario.getDataNascimento().toString());
        document.put("cargo", funcionario.getCargo());
        document.put("cpf", funcionario.getCpf());
        document.put("rg", funcionario.getRg());
        document.put("endereco", funcionario.getEndereco());
        document.put("email", funcionario.getEmail());

        Document query = new Document();
        query.append("codigoFuncionario", codigoFuncionario);

        getCollection().replaceOne(query, document);

        funcionario.setCodigoFuncionario(codigoFuncionario);

        return funcionario;
    }

    @Override
    public List<Funcionario> findAll() throws JsonProcessingException {
        List<Funcionario> funcionarios = new ArrayList<>();

        getCollection().find().forEach(func -> {
            try {
                funcionarios.add(mapFuncionario(func));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        return funcionarios;
    }

    @Override
    public Funcionario findFuncionarioById(String codigoFuncionario) throws JsonProcessingException {
        Document document = findDocumentById(codigoFuncionario);

        return mapFuncionario(document);
    }

    @Override
    public void deleteFuncionarioById(String codigoFuncionario) {
        Document query = new Document();
        query.append("codigoFuncionario", codigoFuncionario);

        getCollection().deleteOne(query);
    }

    private Document findDocumentById(String codigoFuncionario) {
        Document document =
                getCollection().find(eq("codigoFuncionario", codigoFuncionario)).first();

        return document;
    }

    private Funcionario mapFuncionario(Document document) throws JsonProcessingException {
        String json = document.toJson();
        Funcionario funcionario = om.readValue(json, Funcionario.class);

        return funcionario;
    }
}
