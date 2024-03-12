package th.ac.ku.atm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import th.ac.ku.atm.model.Compte;
import th.ac.ku.atm.model.User;
import th.ac.ku.atm.repository.UserRepositoryModel;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserRepositoryModel repository;

  @GetMapping("all")
  public String getBankAccountPage(Model model) {
    List<User> all = repository.findAll();
    model.addAttribute("alluser", all);
    return "WEB-INF/crudClient";
  }

  @GetMapping("login2")
  public String loginPage(Model model) {

    return "WEB-INF/login";
  }

  @PostMapping("login2")
  public String login(HttpSession session, String email, String password, Model model) {
    // public String login(@RequestBody String email,@RequestBody String
    // password,Model model) {
    User user = repository.login(email, password);
    if (user != null) {
      session.setAttribute("user", user);

      // model.addAttribute("login",user);
      System.out.println("ok" + email + " " + password);
      return "WEB-INF/crudClient";
    }
    System.out.println("nok" + email + " " + password);
    model.addAttribute("eroor", "true");

    return "WEB-INF/login";

  }

  @GetMapping("/profile")
  public String profile(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    return "WEB-INF/profile";
  }

  @GetMapping("/logout")
  public String logout(Model model, HttpSession session) {
     session.removeAttribute("user"); 

    return "WEB-INF/login";
  }

  @GetMapping("/create")
public String create(){
    return "WEB-INF/createUser";
}

@PostMapping("/create")
public String createUser(User user){
repository.save(user);
return "redirect:../user/all";

}

@DeleteMapping("/delete/{id}")
// @RequestMapping(value="/delete/{id}",  method = RequestMethod.DELETE,  headers = "Accept=application/json")

public String delete(@PathVariable int id){

     User user= repository.getOne(id);
    repository.delete(user);
    return "redirect:../all";

}


@SuppressWarnings("deprecation")
  @GetMapping("/edit/{id}")
  public String update(@PathVariable int id, Model model) {
    User usr=repository.getOne(id);
    model.addAttribute("user", usr);
    return "WEB-INF/editUser";
  }
  @PostMapping("/update")
  public String update(@ModelAttribute("user") User updatedUser) {
      User existingUser = repository.findById(updatedUser.getId())
              .orElseThrow(() -> new IllegalArgumentException("Invalid compte Id:" + updatedUser.getId()));
      
      existingUser.setPhone(updatedUser.getPhone());
      existingUser.setPassword(updatedUser.getPassword());
      existingUser.setRole(updatedUser.getRole());
      existingUser.setEmail(updatedUser.getEmail());
      existingUser.setFirstName(updatedUser.getFirstName());
      existingUser.setLastName(updatedUser.getLastName());
  
      repository.save(existingUser);
      return "redirect:/user/all";
  }


  
}
