package ifpe.br.rhadminspring.repository;

import ifpe.br.rhadminspring.model.SickNote;

public interface SickNoteRepository {
    SickNote saveSickNote(SickNote sickNote) throws Exception;
}
