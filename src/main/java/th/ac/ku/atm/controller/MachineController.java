package th.ac.ku.atm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import th.ac.ku.atm.model.Machine;
import th.ac.ku.atm.model.User;
import th.ac.ku.atm.repository.MachineRepository;
import th.ac.ku.atm.repository.UserRepositoryModel;
@Controller
@RequestMapping("/machine")
public class MachineController {
    
  @Autowired MachineRepository repository;
 
    @GetMapping("all")
    public String getBankAccountPage(Model model) {
      List<Machine> all= repository.findAll();
        model.addAttribute("alllog",all);
        return "WEB-INF/logCrud";
    }
   
}
