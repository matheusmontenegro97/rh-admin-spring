package ifpe.br.rhadminspring.controller;

import ifpe.br.rhadminspring.model.SickNote;
import ifpe.br.rhadminspring.repository.SickNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh/api/sickNote")
public class SickNoteController {

    @Autowired
    private SickNoteRepository sickNoteRepository;

    @PostMapping
    public ResponseEntity<SickNote> saveSickNote(@RequestBody SickNote sickNote) throws Exception {

        return ResponseEntity.ok(sickNoteRepository.saveSickNote(sickNote));
    }

}
