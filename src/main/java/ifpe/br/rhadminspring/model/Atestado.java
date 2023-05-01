package ifpe.br.rhadminspring.model;

public class Atestado {
    private String codigoAtestado;
    private String codigoFuncionario;
    private String atestado;

    public Atestado() {
    }

    public Atestado(String codigoAtestado, String codigoFuncionario, String atestado) {
        this.codigoAtestado = codigoAtestado;
        this.codigoFuncionario = codigoFuncionario;
        this.atestado = atestado;
    }

    public String getCodigoAtestado() {
        return codigoAtestado;
    }

    public void setCodigoAtestado(String codigoAtestado) {
        this.codigoAtestado = codigoAtestado;
    }

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getAtestado() {
        return atestado;
    }

    public void setAtestado(String atestado) {
        this.atestado = atestado;
    }
}

