package ifpe.br.rhadminspring.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import ifpe.br.rhadminspring.exceptions.FuncionarioNotFoundException;
import ifpe.br.rhadminspring.model.Funcionario;

import java.util.List;

public interface FuncionarioRepository {
    Funcionario saveFuncionario(Funcionario funcionario);

    Funcionario updateFuncionario(String codigoFuncionario, Funcionario funcionario) throws FuncionarioNotFoundException;

    List<Funcionario> findAll() throws JsonProcessingException;

    Funcionario findFuncionarioById(String codigoFuncionario) throws JsonProcessingException;

    String deleteFuncionarioById(String codigoFuncionario);
}

