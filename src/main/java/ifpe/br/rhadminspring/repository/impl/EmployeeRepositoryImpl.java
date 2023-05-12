package ifpe.br.rhadminspring.repository.impl;


import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import ifpe.br.rhadminspring.config.RoleCodec;
import ifpe.br.rhadminspring.config.AddressCodec;
import ifpe.br.rhadminspring.config.EmployeeCodec;
import ifpe.br.rhadminspring.model.Employee;
import ifpe.br.rhadminspring.repository.EmployeeRepository;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private MongoClient mongoClient;

    public MongoCollection<Employee> getCollection() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(
                        PojoCodecProvider.builder().automatic(true)
                                .register(AddressCodec.class)
                                .register(RoleCodec.class)
                                .register(EmployeeCodec.class)
                                .build()
                )
        );
        return mongoClient.getDatabase("rhadmin-spring").getCollection("employee", Employee.class)
                .withCodecRegistry(pojoCodecRegistry);
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        employee.setEmployeeCode(UUID.randomUUID().toString());

        getCollection().insertOne(employee);

        return employee;
    }

    @Override
    public Employee updateEmployee(String employeeCode, Employee employee) {

        employee.setEmployeeCode(employeeCode);
        getCollection().replaceOne(Filters.eq("employeeCode", employeeCode), employee);
        return employee;
    }

    @Override
    public List<Employee> findAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        FindIterable<Employee> employeeFindIterable = getCollection().find();

        for (Employee employee : employeeFindIterable) {
            employees.add(employee);
        }

        return employees;
    }

    @Override
    public Employee findEmployeeById(String employeeCode) {
        Employee employee =
                getCollection().find(eq("employeeCode", employeeCode)).first();

        return employee;
    }

    @Override
    public String deleteEmployeeById(String employeeCode) {
        getCollection().deleteOne(eq("employeeCode", employeeCode));
        return employeeCode;
    }
}