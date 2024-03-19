package th.ac.ku.atm.service;
 
import org.springframework.beans.factory.annotation.Autowired;

import th.ac.ku.atm.model.User;
import th.ac.ku.atm.repository.UserRepositoryModel;

import java.util.List;

public class UserDao {

    @Autowired
    UserRepositoryModel repository;

    public User getById(int id) {
        return repository.getOne(id);

    }

    public List<User> getAll() {
        return repository.findAll();
    }

    // public List<User> chercher(String chercher) {
    //     return repository.findByUsername(chercher);
    // }

    public void save(User user) {
          repository.save(user);
    }

    public void delete(User user) {
          repository.delete(user);
    }

    public User getUserByEmail(String email) {
        return ((UserDao) repository).getUserByEmail(email);
    }

    public User getUserDetails() {
        return null;
    }


}
