package ifpe.br.rhadminspring.model;

import java.io.File;

public class Atestado {
    private String codigoAtestado;
    private String codigoFuncionario;
    private File atestado;

    public Atestado() {
    }

    public Atestado(String codigoFuncionario, File atestado) {
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

    public File getAtestado() {
        return atestado;
    }

    public void setAtestado(File atestado) {
        this.atestado = atestado;
    }
}

