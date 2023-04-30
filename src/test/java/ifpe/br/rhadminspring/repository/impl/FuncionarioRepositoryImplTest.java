package ifpe.br.rhadminspring.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.UUID;

import com.mongodb.client.*;
import ifpe.br.rhadminspring.model.Funcionario;
import ifpe.br.rhadminspring.repository.impl.FuncionarioRepositoryImpl;
import org.bson.Document;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(MockitoExtension.class)
class FuncionarioRepositoryImplTest {

    @Mock
    MongoClient mongoClient;

    @InjectMocks
    FuncionarioRepositoryImpl funcionarioRepositoryImpl;

    @Mock
    MongoDatabase database;

    @Mock
    MongoCollection<Document> coll;

    @Mock
    private FindIterable<Document> findIterable;

    @BeforeEach
    void beforeEach(){
        resetAllMocks();
    }

    void resetAllMocks(){
        reset(mongoClient);
        reset(database);
        reset(coll);
        reset(findIterable);
    }

    @Test
    void saveFuncionarioTest(){
        when(mongoClient.getDatabase(anyString())).thenReturn(database);
        when(database.getCollection(anyString())).thenReturn(coll);

        Funcionario func = new Funcionario();
        func.setDataNascimento(LocalDate.now());
        Document doc = new Document();

        funcionarioRepositoryImpl.saveFuncionario(func);

        verify(coll, times(1)).insertOne(doc);
    }
}
