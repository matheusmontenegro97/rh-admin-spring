package ifpe.br.rhadminspring.repository;

import ifpe.br.rhadminspring.model.Atestado;

public interface AtestadoRepository {
    Atestado saveAtestado(Atestado atestado) throws Exception;
}
