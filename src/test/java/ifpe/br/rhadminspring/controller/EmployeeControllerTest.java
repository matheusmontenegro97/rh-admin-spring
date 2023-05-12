package ifpe.br.rhadminspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ifpe.br.rhadminspring.exceptions.EmployeeNotFoundException;
import ifpe.br.rhadminspring.model.Employee;
import ifpe.br.rhadminspring.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void getEmployeesSuccessTest() throws JsonProcessingException {
        Employee employee = new Employee();
        employee.setName("name");

        when(employeeRepository.findAllEmployees()).thenReturn(Arrays.asList(employee));

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        assertEquals(Arrays.asList(employee), response.getBody());
    }

    @Test
    void getEmployeeByIdSuccessTest() throws JsonProcessingException {
        Employee employee = new Employee();
        employee.setName("name");

        when(employeeRepository.findEmployeeById(anyString())).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(UUID.randomUUID().toString());

        assertEquals(employee, response.getBody());
    }

    @Test
    void updateEmployeeSuccessTest() throws EmployeeNotFoundException {
        Employee employee = new Employee();
        employee.setName("name");

        when(employeeRepository.updateEmployee(anyString(), any())).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(UUID.randomUUID().toString(), employee);

        assertEquals(employee, response.getBody());
    }

    @Test
    void saveEmployeeSuccessTest() {
        Employee employee = new Employee();
        employee.setName("name");

        when(employeeRepository.saveEmployee(any())).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.saveEmployee(employee);

        assertEquals(employee, response.getBody());
    }

    @Test
    void deleteEmployeeSuccessTest() {
        String employeeCode = UUID.randomUUID().toString();
        String message = employeeCode;

        when(employeeRepository.deleteEmployeeById(anyString())).thenReturn(message);

        ResponseEntity<String> response = employeeController.deleteEmployee(employeeCode);

        assertEquals(message, response.getBody());
    }
}
