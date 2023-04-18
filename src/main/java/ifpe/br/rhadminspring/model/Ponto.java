package ifpe.br.rhadminspring.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ponto {
    private String codigoPonto;
    private String codigoFuncionario;
    private LocalTime horaEntradaTrabalho;
    private LocalTime horaSaidaAlmoco;
    private LocalTime horaVoltaAlmoco;
    private LocalTime horaSaidaTrabalho;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data;

    public String getCodigoPonto() {
        return codigoPonto;
    }

    public void setCodigoPonto(String codigoPonto) {
        this.codigoPonto = codigoPonto;
    }

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public LocalTime getHoraEntradaTrabalho() {
        return horaEntradaTrabalho;
    }

    public void setHoraEntradaTrabalho(LocalTime horaEntradaTrabalho) {
        this.horaEntradaTrabalho = horaEntradaTrabalho;
    }

    public LocalTime getHoraSaidaAlmoco() {
        return horaSaidaAlmoco;
    }

    public void setHoraSaidaAlmoco(LocalTime horaSaidaAlmoco) {
        this.horaSaidaAlmoco = horaSaidaAlmoco;
    }

    public LocalTime getHoraVoltaAlmoco() {
        return horaVoltaAlmoco;
    }

    public void setHoraVoltaAlmoco(LocalTime horaVoltaAlmoco) {
        this.horaVoltaAlmoco = horaVoltaAlmoco;
    }

    public LocalTime getHoraSaidaTrabalho() {
        return horaSaidaTrabalho;
    }

    public void setHoraSaidaTrabalho(LocalTime horaSaidaTrabalho) {
        this.horaSaidaTrabalho = horaSaidaTrabalho;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}

