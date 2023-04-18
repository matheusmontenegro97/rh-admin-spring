package ifpe.br.rhadminspring.repository;

import ifpe.br.rhadminspring.model.Atestado;
import org.springframework.web.multipart.MultipartFile;

public interface AtestadoRepository {
    Atestado saveAtestado(MultipartFile atestado, String codigoFuncionario) throws Exception;
}
