package ifpe.br.rhadminspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ifpe.br.rhadminspring.exceptions.FuncionarioNotFoundException;
import ifpe.br.rhadminspring.model.Funcionario;
import ifpe.br.rhadminspring.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/rh/api")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @GetMapping
    public ResponseEntity<List<Funcionario>> getFuncionarios() throws JsonProcessingException {
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionario
            (@PathVariable("id") String id) throws JsonProcessingException {
        return ResponseEntity.ok(funcionarioRepository.findFuncionarioById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario
            (@PathVariable("id") String id, @RequestBody Funcionario funcionario) throws FuncionarioNotFoundException {
        return ResponseEntity.ok(funcionarioRepository.updateFuncionario(id, funcionario));
    }

    @PostMapping
    public ResponseEntity<Funcionario> saveFuncionario(@RequestBody Funcionario funcionario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioRepository.saveFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFuncionario(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(funcionarioRepository.deleteFuncionarioById(id));
    }
}
