package th.ac.ku.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import th.ac.ku.atm.model.User;
import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface UserRepositoryModel extends JpaRepository<User, Integer> {
    // don't forget to change root password to the actual one
    // List<User> findByUsername(String username);

    User findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    @Query(value = " Select * FROM users where email=?1 and password=?2", nativeQuery = true)
    User login(String email, String password);

}
