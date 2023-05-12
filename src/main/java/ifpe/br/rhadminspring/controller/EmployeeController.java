package ifpe.br.rhadminspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ifpe.br.rhadminspring.exceptions.EmployeeNotFoundException;
import ifpe.br.rhadminspring.model.Employee;
import ifpe.br.rhadminspring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() throws JsonProcessingException {
        return ResponseEntity.ok(employeeRepository.findAllEmployees());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById
            (@PathVariable("id") String id) throws JsonProcessingException {
        return ResponseEntity.ok(employeeRepository.findEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee
            (@PathVariable("id") String id, @RequestBody Employee employee) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeRepository.updateEmployee(id, employee));
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.saveEmployee(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(employeeRepository.deleteEmployeeById(id));
    }
}
