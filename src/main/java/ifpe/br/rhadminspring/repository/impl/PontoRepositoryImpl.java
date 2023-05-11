package ifpe.br.rhadminspring.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import ifpe.br.rhadminspring.config.PontoCodec;
import ifpe.br.rhadminspring.exceptions.FuncionarioNotFoundException;
import ifpe.br.rhadminspring.model.Ponto;
import ifpe.br.rhadminspring.repository.FuncionarioRepository;
import ifpe.br.rhadminspring.repository.PontoRepository;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class PontoRepositoryImpl implements PontoRepository {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public MongoCollection<Ponto> getCollection() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(
                        PojoCodecProvider.builder().automatic(true)
                                .register(PontoCodec.class)
                                .build()
                )
        );
        return mongoClient.getDatabase("rhadmin-spring").getCollection("ponto", Ponto.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

    public Ponto savePonto(Ponto ponto) throws FuncionarioNotFoundException {

        if (Objects.isNull(funcionarioRepository.findFuncionarioById(ponto.getCodigoFuncionario())))
            throw new FuncionarioNotFoundException(String.format("Funcionario com id: %s não encontrado", ponto.getCodigoFuncionario()));

        ponto.setCodigoPonto(UUID.randomUUID().toString());

        getCollection().insertOne(ponto);

        return ponto;
    }
}

