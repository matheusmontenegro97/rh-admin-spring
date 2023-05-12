package ifpe.br.rhadminspring.exceptions;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

