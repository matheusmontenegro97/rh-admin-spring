package ifpe.br.rhadminspring.repository.impl;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import ifpe.br.rhadminspring.config.SickNoteCodec;
import ifpe.br.rhadminspring.exceptions.EmployeeNotFoundException;
import ifpe.br.rhadminspring.model.SickNote;
import ifpe.br.rhadminspring.repository.SickNoteRepository;
import ifpe.br.rhadminspring.repository.EmployeeRepository;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Component
public class SickNoteRepositoryImpl implements SickNoteRepository {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    public MongoCollection<SickNote> getCollection() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(
                        PojoCodecProvider.builder().automatic(true)
                                .register(SickNoteCodec.class)
                                .build()
                )
        );
        return mongoClient.getDatabase("rhadmin-spring").getCollection("sickNote", SickNote.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

    private GridFSBucket getGridFSBuckets() {
        return GridFSBuckets.create(mongoClient.getDatabase("rhadmin-quarkus"));
    }

    public SickNote saveSickNote(SickNote sickNote) throws Exception {

        if (Objects.isNull(employeeRepository.findEmployeeById(sickNote.getEmployeeCode())))
            throw new EmployeeNotFoundException(String.format("Employee with id: %s not found", sickNote.getEmployeeCode()));

        sickNote.setSickNoteCode(UUID.randomUUID().toString());

        File file = new File(sickNote.getSickNote());
        InputStream targetStream = new FileInputStream(file);
        getGridFSBuckets().uploadFromStream(sickNote.getSickNoteCode(), targetStream);

        getCollection().insertOne(sickNote);

        return sickNote;
    }
}