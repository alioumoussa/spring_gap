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

    // public User getUserByEmailAndPassword(String email, String password) {
    //     try (Session session = openSession()) {
    //         Query<User> query = session.createQuery("FROM User WHERE email = :email AND password = :password",
    //                 User.class);
    //         query.setParameter("email", email);
    //         query.setParameter("password", password);
    //         return query.uniqueResult();
    //     } catch (RuntimeException e) {
    //          
    //          
    //         e.printStackTrace();
    //         throw new RuntimeException(
    //                 "Une erreur s'est produite lors de la recherche de l'utilisateur par e-mail et mot de passe.", e);
    //     }
    // }

    
    // public void registerUser(String firstName, String lastName, String email, String phone, String password,
    //         String role) {
    //     if (getUserByEmail(email) == null) {
    //         User newUser = new User(firstName, lastName, email, phone, password, role);
    //         saveOrUpdate(newUser);
    //     } else {
    //         throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
    //     }
    // }

    // Méthode de connexion d'un utilisateur
    // public User loginUser(String email, String password) {
    //     try {
    //         User user = getUserByEmailAndPassword(email, password);
    //         if (user != null) {
    //             return user;
    //         }
    //     } catch (RuntimeException e) {
    //         e.printStackTrace();
    //         // throw new RuntimeException("Identifiants invalides. Veuillez réessayer.");
    //     }
    //     return null;
    // }

    // ... autres méthodes de UserService
}
