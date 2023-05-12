package ifpe.br.rhadminspring.repository;

import ifpe.br.rhadminspring.model.Clocking;

public interface ClockingRepository {

    Clocking saveClocking(Clocking clocking) throws Exception;

}