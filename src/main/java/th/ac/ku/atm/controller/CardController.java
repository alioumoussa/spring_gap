package th.ac.ku.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import th.ac.ku.atm.model.Carte;
import th.ac.ku.atm.service.CarteDao;

@Controller
public class CardController {

    @Autowired
    private CarteDao carteDao;

    @GetMapping("/cardUpload")
    public String handlePdfUpload(@RequestParam("noCarte") String noCarte) {
        // Effectuer les actions nécessaires avec le numéro de carte
        // Par exemple, rediriger vers une autre page ou effectuer un traitement spécifique

        // Redirection vers une page de confirmation
        return "redirect:/code";
    }

    @PostMapping("/card/verifyPdf")
    public String verifyPdf(@RequestParam("noCarte") String cardNumber, Model model) {
        Carte carte = carteDao.getCarteByPin(cardNumber);
        if (carte != null) {
            // Carte valide, rediriger vers la page gab.html
            return "redirect:/gab";
        } else {
            // Carte invalide, ajouter un attribut d'erreur et rediriger vers la page code
            model.addAttribute("error", true);
            return "code";
        }
    }
}
