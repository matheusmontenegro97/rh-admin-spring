package ifpe.br.rhadminspring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Funcionario {
    private String codigoFuncionario;
    private String nome;
    private String nomeSocial;
    private Cargo cargo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String cpf;
    private String rg;
    private Endereco endereco;
    private String email;

    public Funcionario() {
    }

    public Funcionario(String codigoFuncionario, String nome, String nomeSocial, Cargo cargo, LocalDate dataNascimento, String cpf, String rg, Endereco endereco, String email) {
        this.codigoFuncionario = codigoFuncionario;
        this.nome = nome;
        this.nomeSocial = nomeSocial;
        this.cargo = cargo;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.email = email;
    }

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return codigoFuncionario.equals(that.codigoFuncionario) && nome.equals(that.nome) && nomeSocial.equals(that.nomeSocial) && cargo.equals(that.cargo) && dataNascimento.equals(that.dataNascimento) && cpf.equals(that.cpf) && rg.equals(that.rg) && endereco.equals(that.endereco) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoFuncionario, nome, nomeSocial, cargo, dataNascimento, cpf, rg, endereco, email);
    }
}

