package th.ac.ku.atm.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import th.ac.ku.atm.model.Carte;
import th.ac.ku.atm.model.Compte;
import th.ac.ku.atm.model.Machine;
import th.ac.ku.atm.model.User;
import th.ac.ku.atm.repository.CarteRepository;
import th.ac.ku.atm.repository.CompteRepository;

@Controller
@RequestMapping("/atm")
public class GabController {

    @Autowired
    CarteRepository repository;
    @Autowired
    CompteRepository comteRepo;

    private int remainingAttempts = 3;

    @GetMapping("code")
    public String getCode() {

        return "WEB-INF/code";
    }

    @GetMapping()
    public String getCodePdf() {

        return "WEB-INF/index";
    }

    @PostMapping("verify")
    String verify(String noCarte, Model model) {
        Carte crt = repository.getCartByPin(Integer.parseInt(noCarte));
        if (crt != null) {
            // Si le code PIN est correct, réinitialise le nombre d'essais restants
            remainingAttempts = 3;
            Compte cmpt = crt.getCompte();
            User user = cmpt.getUser();
            model.addAttribute("carte", crt);
            model.addAttribute("user", user);
            model.addAttribute("compte", cmpt);
            return "WEB-INF/gab"; // Remplacez avec la page appropriée
        } else {
            // Si le code PIN est incorrect, décrémente le nombre d'essais restants
            remainingAttempts--;
            model.addAttribute("error", true);
            model.addAttribute("remainingAttempts", remainingAttempts);
            return "WEB-INF/code";
        }
    }


    @GetMapping("ticket")
    String printTicket(Model model) {
        // Ajoutez les attributs nécessaires pour l'impression du ticket
        return "WEB-INF/ticket";
    }

    @PostMapping("withdraw")
    String withdraw(String compteId, String montant, String noCarte, Model model) {
        // Récupérer le compte et effectuer le retrait
        Compte cmpt = comteRepo.getById(Integer.parseInt(compteId));
        Double montant2 = Double.parseDouble(montant);
System.out.println(compteId+" deidine "+montant+" "+noCarte);
        // Vérifier si le solde est suffisant pour le retrait
        if (cmpt != null && cmpt.getSolde() >= montant2) {
            cmpt.setSolde(cmpt.getSolde() - montant2);
            comteRepo.save(cmpt);

String date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

            // Ajouter les attributs nécessaires pour l'impression du ticket
            model.addAttribute("carte", repository.getCartByPin(Integer.parseInt(noCarte)));
            model.addAttribute("user", cmpt.getUser());
            model.addAttribute("compte", cmpt);
            model.addAttribute("date", date);
            model.addAttribute("timeStamp", timeStamp);
            model.addAttribute("montant", montant2);
// User u=cmpt.getUser();
// u.getFirstName()
// u.getLastName()
// cmpt.getSolde()
            return "WEB-INF/ticket"; // Rediriger vers la page ticket.html après le retrait
        } else {
            // Gérer le cas où le solde est insuffisant
            model.addAttribute("error", "true");
            return "WEB-INF/code";
        }
    }



}