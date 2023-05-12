package ifpe.br.rhadminspring.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import ifpe.br.rhadminspring.exceptions.EmployeeNotFoundException;
import ifpe.br.rhadminspring.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee saveEmployee(Employee employee);

    Employee updateEmployee(String employeeCode, Employee employee) throws EmployeeNotFoundException;

    List<Employee> findAllEmployees() throws JsonProcessingException;

    Employee findEmployeeById(String employeeCode);

    String deleteEmployeeById(String employeeCode);
}

