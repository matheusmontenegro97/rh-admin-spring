package ifpe.br.rhadminspring.controller;

import ifpe.br.rhadminspring.model.Atestado;
import ifpe.br.rhadminspring.repository.AtestadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh/api/atestado")
public class AtestadoController {

    @Autowired
    private AtestadoRepository atestadoRepository;

    @PostMapping
    public ResponseEntity<Atestado> saveAtestado(@RequestBody Atestado atestado) throws Exception {

        return ResponseEntity.ok(atestadoRepository.saveAtestado(atestado));
    }

}
