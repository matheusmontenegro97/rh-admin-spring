package ifpe.br.rhadminspring.controller;

import ifpe.br.rhadminspring.model.Atestado;
import ifpe.br.rhadminspring.repository.AtestadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtestadoControllerTest {

    @Mock
    private AtestadoRepository atestadoRepository;

    @InjectMocks
    private AtestadoController atestadoController;


    @Test
    void saveAtestadoSuccessTest() throws Exception {
        Atestado atestado = new Atestado();
        atestado.setCodigoFuncionario(UUID.randomUUID().toString());
        atestado.setAtestado("/C:/Users/mathe/Downloads/693876.jpg");

        when(atestadoRepository.saveAtestado(any())).thenReturn(atestado);

        ResponseEntity<Atestado> response = atestadoController.saveAtestado(atestado);

        assertEquals(200, response.getStatusCode().value());
    }
}
