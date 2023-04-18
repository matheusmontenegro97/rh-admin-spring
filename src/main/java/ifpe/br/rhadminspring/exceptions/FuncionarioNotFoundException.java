package ifpe.br.rhadminspring.exceptions;

public class FuncionarioNotFoundException extends Exception {

    public FuncionarioNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

