package th.ac.ku.atm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import th.ac.ku.atm.model.Transaction;
import th.ac.ku.atm.model.User;
import th.ac.ku.atm.repository.TransactionRepository;
import th.ac.ku.atm.repository.UserRepositoryModel;
@Controller
@RequestMapping("/transaction")
public class TransactionController {
    
  @Autowired TransactionRepository repository;
 
    @GetMapping("all")
    public String getBankAccountPage(Model model) {
      List<Transaction> all= repository.findAll();
        model.addAttribute("alltrans",all);
        return "WEB-INF/transaction";
    }
   
}
