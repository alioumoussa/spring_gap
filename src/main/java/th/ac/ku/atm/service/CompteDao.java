package th.ac.ku.atm.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.ku.atm.model.Compte;
import th.ac.ku.atm.repository.CompteRepository;

import java.util.List;
@Service
public class CompteDao {

    @Autowired  CompteRepository repository ;
    public Compte getById(int id) {
        return repository.getOne(id);
    }

    public List<Compte> chercher(String num) {
        return repository.findAll();
    }

    public List<Compte> getAll() {

        return repository.findAll();

    }

    public void save(Compte compte) {
        repository.save(compte);
    }

    public void delete(Compte compte) {
        repository.delete(compte);
    }

    public void update(Compte cmt, int id) {
        Compte existe = this.getById(id);

        existe.setNumCompte(cmt.getNumCompte());
        existe.setSolde(cmt.getSolde());
        existe.setType_compte(cmt.getType_compte());
        existe.setUser(cmt.getUser());
        repository.save(existe);
    }

    // public List<Compte> getComptesByUserId(Long userId) {
    // try (Session session = openSession()) {
    // Query<Compte> query = session.createQuery("FROM Compte WHERE user.id =
    // :userId", Compte.class);
    // query.setParameter("userId", userId);
    // return query.list();
    // }
    // }

    // ... autres méthodes de CompteDao
}
