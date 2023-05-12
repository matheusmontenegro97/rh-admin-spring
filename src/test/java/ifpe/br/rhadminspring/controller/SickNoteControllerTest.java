package ifpe.br.rhadminspring.controller;

import ifpe.br.rhadminspring.model.SickNote;
import ifpe.br.rhadminspring.repository.SickNoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SickNoteControllerTest {

    @Mock
    private SickNoteRepository sickNoteRepository;

    @InjectMocks
    private SickNoteController sickNoteController;


    @Test
    void saveSickNoteSuccessTest() throws Exception {
        SickNote sickNote = new SickNote();
        sickNote.setEmployeeCode(UUID.randomUUID().toString());
        sickNote.setSickNote("/C:/Users/mathe/Downloads/693876.jpg");

        when(sickNoteRepository.saveSickNote(any())).thenReturn(sickNote);

        ResponseEntity<SickNote> response = sickNoteController.saveSickNote(sickNote);

        assertEquals(sickNote, response.getBody());
    }
}
