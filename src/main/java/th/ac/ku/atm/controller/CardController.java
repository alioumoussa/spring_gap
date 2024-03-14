package th.ac.ku.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import th.ac.ku.atm.model.Carte;
import th.ac.ku.atm.model.Compte;
import th.ac.ku.atm.model.User;
import th.ac.ku.atm.service.CarteDao;

@Controller
public class CardController {


        @Autowired
        private CarteDao carteDao;

        @GetMapping("/card/upload")
        public String uploadCardForm() {
            return "code"; // retourne la vue "code.html" pour insérer le code PIN
        }

    @PostMapping("/card/validate")
    public String validatePin(@RequestParam("noCarte") String pinString, Model model) {
        // Convertir la chaîne de caractères en entier
        int pin = Integer.parseInt(pinString);
        Carte carte = carteDao.getCarteByPin(pin);
        if (carte != null) {
            Compte compte = carte.getCompte();
            User user = compte.getUser();

            model.addAttribute("carte", carte);
            model.addAttribute("client", user);
            model.addAttribute("compte", compte);

            // Effectuer d'autres traitements nécessaires
            return "index"; // Redirige vers la vue "index.html"
        } else {
            model.addAttribute("error", true);
            return "code"; // Redirige vers la vue "code.html" en cas d'erreur
        }
    }


}


