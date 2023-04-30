package ifpe.br.rhadminspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ifpe.br.rhadminspring.model.Funcionario;
import ifpe.br.rhadminspring.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FuncionarioControllerTest {

    @Mock
    FuncionarioRepository funcionarioRepository;

    @InjectMocks
    FuncionarioController funcionarioController;

    @Test
    void getFuncionariosSuccessTest() throws JsonProcessingException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("name");

        when(funcionarioRepository.findAll()).thenReturn(Arrays.asList(funcionario));

        ResponseEntity<List<Funcionario>> response = funcionarioController.getFuncionarios();

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void getFuncionarioByIdSuccessTest() throws JsonProcessingException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("name");

        when(funcionarioRepository.findFuncionarioById(anyString())).thenReturn(funcionario);

        ResponseEntity<Funcionario> response = funcionarioController.getFuncionario(UUID.randomUUID().toString());

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void updateFuncionarioSuccessTest(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("name");

        when(funcionarioRepository.updateFuncionario(anyString(),any())).thenReturn(funcionario);

        ResponseEntity<Funcionario> response = funcionarioController.updateFuncionario(UUID.randomUUID().toString(), funcionario);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void saveFuncionarioSuccessTest(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("name");

        when(funcionarioRepository.saveFuncionario(any())).thenReturn(funcionario);

        ResponseEntity<Funcionario> response = funcionarioController.saveFuncionario(funcionario);

        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void deleteFuncionarioSucessTest(){
        String codigoFuncionario = UUID.randomUUID().toString();
        String message = format("Funcionário com codigoFuncionario %s foi deletado com sucesso",codigoFuncionario);

        when(funcionarioRepository.deleteFuncionarioById(anyString())).thenReturn(message);

        ResponseEntity<String> response = funcionarioController.deleteFuncionario(codigoFuncionario);

        assertEquals(204, response.getStatusCode().value());
    }
}
