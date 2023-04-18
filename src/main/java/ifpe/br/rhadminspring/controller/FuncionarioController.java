package ifpe.br.rhadminspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ifpe.br.rhadminspring.model.Funcionario;
import ifpe.br.rhadminspring.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/api")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> getFuncionarios() throws JsonProcessingException {
        return funcionarioRepository.findAll();
    }


    @GetMapping("/{id}")
    public Funcionario getFuncionario
            (@PathVariable("id") String id) throws JsonProcessingException {
        return funcionarioRepository.findFuncionarioById(id);
    }

    @PutMapping("/{id}")
    public Funcionario updateFuncionarios
            (@PathVariable("id") String id, @RequestBody Funcionario funcionario) {
        return funcionarioRepository.updateFuncionario(id, funcionario);
    }

    @PostMapping
    public Funcionario addFuncionario(@RequestBody Funcionario funcionario) {

        return funcionarioRepository.saveFuncionario(funcionario);
    }

    @DeleteMapping("/{id}")
    public void deleteFuncionario(@PathVariable("id") String id) {
        funcionarioRepository.deleteFuncionarioById(id);
    }
}
