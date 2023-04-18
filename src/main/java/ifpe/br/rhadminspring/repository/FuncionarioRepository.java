package ifpe.br.rhadminspring.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import ifpe.br.rhadminspring.model.Funcionario;

import java.util.List;

public interface FuncionarioRepository {
    Funcionario saveFuncionario(Funcionario funcionario);

    Funcionario updateFuncionario(String codigoFuncionario, Funcionario funcionario);

    List<Funcionario> findAll() throws JsonProcessingException;

    Funcionario findFuncionarioById(String codigoFuncionario) throws JsonProcessingException;

    void deleteFuncionarioById(String codigoFuncionario);
}
