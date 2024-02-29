package th.ac.ku.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.atm.model.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {
    // don't forget to change root password to the actual one

}
