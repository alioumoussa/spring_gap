package th.ac.ku.atm.service;
 
import org.springframework.stereotype.Service;

import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.repository.CustomerRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {
    
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void createCustomer(Customer customer) {
        String hashPin = hash(customer.getPin());
        customer.setPin(hashPin);
        repository.save(customer);
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer findCustomer(int id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Customer checkPin(Customer inputCustomer) {
        Customer storedCustomer = findCustomer(inputCustomer.getId());
        if (storedCustomer != null) {
            String hashPin = storedCustomer.getPin();
            if (org.springframework.security.crypto.bcrypt.BCrypt.checkpw(inputCustomer.getPin(), hashPin)) {
                return storedCustomer;
            }
        }
        return null;
    }

    private String hash(String pin) {
        String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt(12);
        return org.springframework.security.crypto.bcrypt.BCrypt.hashpw(pin, salt);
    }
}
