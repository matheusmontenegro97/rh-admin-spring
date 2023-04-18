package ifpe.br.rhadminspring.controller;

import ifpe.br.rhadminspring.model.Ponto;
import ifpe.br.rhadminspring.repository.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh/api/ponto")
public class PontoController {

    @Autowired
    PontoRepository pontoRepository;

    @PostMapping
    public Ponto savePonto(@RequestBody Ponto ponto) throws Exception {
        return pontoRepository.savePonto(ponto);
    }
}
