package th.ac.ku.atm.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import th.ac.ku.atm.model.Carte;
import th.ac.ku.atm.model.Compte;
import th.ac.ku.atm.model.User;
import th.ac.ku.atm.model.Compte;
import th.ac.ku.atm.repository.CarteRepository;
import th.ac.ku.atm.repository.CompteRepository;
import th.ac.ku.atm.repository.UserRepositoryModel;
import th.ac.ku.atm.service.CarteDao;
import th.ac.ku.atm.service.CompteDao;

@Controller
@RequestMapping("/compte")
public class CompteController {

  @Autowired
  CompteRepository repository;
  @Autowired UserRepositoryModel userRepo;
@Autowired CarteRepository crtrep;
  @GetMapping("all")
  public String getBankAccountPage(Model model) {
    List<Compte> all = repository.findAll();
    model.addAttribute("allCompte", all);
    return "WEB-INF/compteCrud";
  }

  @GetMapping("/associer/{id}")
  public String create(@PathVariable int id, Model model) {
    model.addAttribute("idUser", id);
    return "WEB-INF/createCompte";
  }

  @PostMapping("/create")
  public String createUser(Compte compte,String user_id) {
      // Check if the User associated with the Compte is not null

      User usr=   userRepo.getById(Integer.parseInt(user_id) );
    
      compte.setUser(usr);
      repository.save(compte);
      return "redirect:../compte/all";
  }

  // @DeleteMapping("/delete/{id}")
  // // @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, headers
  // // = "Accept=application/json")

  // public String delete(@PathVariable int id) {
  //   @SuppressWarnings("deprecation")
  //   Compte compte = repository.getOne(id);
    
  //   repository.delete(compte);
  //   return "redirect:../all";

  // }
  @GetMapping("/delete/{id}")
  public String deleteCompte(@PathVariable("id") int id) {
      repository.deleteById(id);
      return "redirect:/compte/all";
  }
  @SuppressWarnings("deprecation")
  @GetMapping("/edit/{id}")
  public String update(@PathVariable int id, Model model) {
    Compte cmpt=repository.getOne(id);
    System.out.println("deidine"+cmpt.getNumCompte());
    model.addAttribute("compte", cmpt);
    model.addAttribute("userId", id);
    return "WEB-INF/editCompte";
  }
  @PostMapping("/update")
  public String update(@ModelAttribute("compte") Compte updatedCompte) {
      Compte existingCompte = repository.findById(updatedCompte.getId())
              .orElseThrow(() -> new IllegalArgumentException("Invalid compte Id:" + updatedCompte.getId()));
      
      existingCompte.setNumCompte(updatedCompte.getNumCompte());
      existingCompte.setSolde(updatedCompte.getSolde());
      existingCompte.setType_compte(updatedCompte.getType_compte());
  
      repository.save(existingCompte);
      return "redirect:/compte/all";
  }

  @GetMapping("/generate/{id}")
  public String genrateCarte(@PathVariable int id, Model model) {
    Compte cmpt=repository.getOne(id); 
     User client=cmpt.getUser();
    Carte crt= this.generateCarte(cmpt);
    model.addAttribute("compte", cmpt);
    model.addAttribute("carte", crt);
    model.addAttribute("client", client); 
    return "WEB-INF/carte";
  }
  	public Carte generateCarte(Compte compte) {
      CarteDao dao=new CarteDao();
      String numeroCarte = dao.generateRandomNumber(16);

		String codePin = dao.generateRandomNumber(4);

		LocalDate localdate = LocalDate.now().plus(2, ChronoUnit.YEARS);
		Date expirationDate = Date.valueOf(localdate);

		Carte carte = new Carte(numeroCarte, codePin, expirationDate, compte);

		crtrep.save(carte);

		return carte;
	}
}
