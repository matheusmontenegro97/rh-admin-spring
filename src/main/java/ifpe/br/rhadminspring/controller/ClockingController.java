package ifpe.br.rhadminspring.controller;

import ifpe.br.rhadminspring.model.Clocking;
import ifpe.br.rhadminspring.repository.ClockingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh/api/clocking")
public class ClockingController {

    @Autowired
    private ClockingRepository clockingRepository;

    @PostMapping
    public ResponseEntity<Clocking> saveClocking(@RequestBody Clocking clocking) throws Exception {
        return ResponseEntity.ok(clockingRepository.saveClocking(clocking));
    }
}
