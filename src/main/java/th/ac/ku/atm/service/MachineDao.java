package th.ac.ku.atm.service;
 
import org.springframework.beans.factory.annotation.Autowired;

import th.ac.ku.atm.model.Machine;
import th.ac.ku.atm.model.User;
import th.ac.ku.atm.repository.MachineRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class MachineDao   {
 
	@Autowired
	MachineRepository repository;
	
	public Machine getById(int id) {
		        return repository.getOne(id);

	}

	 

	public User getUserByMachine(Machine cpt) {
	UserDao user=new UserDao();
	return user.getById(cpt.getId());

	}
	
	public List<Machine> getAll() {
 	return	repository.findAll();
	}


 
	public void save (Machine entity) {
		 
	repository.save(entity);
	}
 
	public void delete(Machine entity) {
		repository.delete(entity);
}
}