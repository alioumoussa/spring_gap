package th.ac.ku.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.atm.model.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
    // don't forget to change root password to the actual one

}
