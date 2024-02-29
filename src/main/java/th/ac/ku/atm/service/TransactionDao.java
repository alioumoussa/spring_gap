package th.ac.ku.atm.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import th.ac.ku.atm.model.Transaction;
import th.ac.ku.atm.model.User;
import th.ac.ku.atm.repository.TransactionRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class TransactionDao {

	@Autowired
	TransactionRepository repository;
 
	public Transaction getById(int id) {
		return repository.getOne(id);

	}

	public User getUserByTransaction(Transaction cpt) {
		UserDao user=new UserDao();

		return user.getById(cpt.getId());
		 
		 

	}

	
	public List<Transaction> getAll() {
		return repository.findAll();

	}
 
	public void save (Transaction entity) {
          repository.save(entity);
	 
	}
 
	public void delete(Transaction entity) {
		  repository.delete(entity);

	}
}
