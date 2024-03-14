package th.ac.ku.atm.controller;

import java.sql.Timestamp;
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

    @PostMapping("withdrow")
    String withdrow(String compteId, String montant, String noCarte, Model model) {

        // Récupérer la date actuelle
        Date date = new Date();

        // Récupérer le timestamp actuel
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Ajouter la date et le timestamp au modèle
        model.addAttribute("date", date);
        model.addAttribute("timestamp", timestamp);

        Compte cmpt = comteRepo.getById(Integer.parseInt(compteId));

        // Vérifiez si la chaîne de montant n'est pas vide avant de la convertir en double
        if (montant != null && !montant.isEmpty()) {
            try {
                Double montant2 = Double.parseDouble(montant);

                if (cmpt != null) {
                    if (cmpt.getSolde() >= montant2) {
                        cmpt.setSolde(cmpt.getSolde() - montant2);
                        comteRepo.save(cmpt);
                        Carte crt = repository.getCartByPin(Integer.parseInt(noCarte));

                        model.addAttribute("montant", montant2);

                        model.addAttribute("carte", crt);
                        model.addAttribute("user", cmpt.getUser());
                        model.addAttribute("compte", cmpt);
                        return "WEB-INF/tiket"; // Rediriger vers la page ticket.html après le retrait
                    }
                    return "WEB-INF/gab";
                } else {
                    model.addAttribute("error", "true");
                    return "WEB-INF/code";
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Traitez le cas où la chaîne ne peut pas être convertie en double
                // Peut-être ajouter un message d'erreur à l'utilisateur ou effectuer une autre action appropriée
                return "WEB-INF/error"; // ou une autre page d'erreur
            }
        } else {
            // Traitez le cas où la chaîne de montant est vide
            model.addAttribute("error", "true");
            return "WEB-INF/code";
        }
    }



}
