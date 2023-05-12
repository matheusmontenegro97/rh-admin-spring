package ifpe.br.rhadminspring.repository.impl;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import ifpe.br.rhadminspring.config.ClockingCodec;
import ifpe.br.rhadminspring.exceptions.EmployeeNotFoundException;
import ifpe.br.rhadminspring.model.Clocking;
import ifpe.br.rhadminspring.repository.EmployeeRepository;
import ifpe.br.rhadminspring.repository.ClockingRepository;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class ClockingRepositoryImpl implements ClockingRepository {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    public MongoCollection<Clocking> getCollection() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(
                        PojoCodecProvider.builder().automatic(true)
                                .register(ClockingCodec.class)
                                .build()
                )
        );
        return mongoClient.getDatabase("rhadmin-spring").getCollection("clocking", Clocking.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

    public Clocking saveClocking(Clocking clocking) throws EmployeeNotFoundException {

        if (Objects.isNull(employeeRepository.findEmployeeById(clocking.getEmployeeCode())))
            throw new EmployeeNotFoundException(String.format("Employee with id: %s not found", clocking.getEmployeeCode()));

        clocking.setClockingCode(UUID.randomUUID().toString());

        getCollection().insertOne(clocking);

        return clocking;
    }
}

