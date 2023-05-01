package ifpe.br.rhadminspring.repository.impl;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import ifpe.br.rhadminspring.exceptions.FuncionarioNotFoundException;
import ifpe.br.rhadminspring.model.Ponto;
import ifpe.br.rhadminspring.repository.FuncionarioRepository;
import ifpe.br.rhadminspring.repository.PontoRepository;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PontoRepositoryImpl implements PontoRepository {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private MongoCollection<Ponto> getCollection() {
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return mongoClient.getDatabase("rhadmin-spring").getCollection("rhadmin-spring", Ponto.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

    public Ponto savePonto(Ponto ponto) throws Exception {

        Optional.of(funcionarioRepository.findFuncionarioById(ponto.getCodigoFuncionario()))
                .orElseThrow(() -> new FuncionarioNotFoundException(String.format("Funcionario com id: %s não encontrado", ponto.getCodigoFuncionario())));

        ponto.setCodigoPonto(UUID.randomUUID().toString());

        getCollection().insertOne(ponto);

        return ponto;
    }
}

