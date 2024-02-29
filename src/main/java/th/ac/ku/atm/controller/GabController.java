package th.ac.ku.atm.controller;

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
        Compte cmpt = crt.getCompte();
        User user = cmpt.getUser();
        if (crt != null) {
            model.addAttribute("carte", crt);
            model.addAttribute("user", user);
            model.addAttribute("compte", cmpt);

            return "WEB-INF/gab";

        } else {
            model.addAttribute("eroor", "true");
            return "WEB-INF/code";
        }

    }

    @PostMapping("withdrow")
    String withdrow(String compteId, String montant, String noCarte, Model model) {

        Compte cmpt = comteRepo.getById(Integer.parseInt(compteId));
        Double montant2 = Double.parseDouble(montant);

        if (cmpt != null) {
            if (cmpt.getSolde() >= montant2) {
                cmpt.setSolde(cmpt.getSolde() - montant2);
                comteRepo.save(cmpt);
                Carte crt = repository.getCartByPin(Integer.parseInt(noCarte));

                model.addAttribute("carte", crt);
                model.addAttribute("user", cmpt.getUser());
                model.addAttribute("compte", cmpt);
            }

            return "WEB-INF/gab";

        } else {
            model.addAttribute("eroor", "true");
            return "WEB-INF/code";
        }

    }

}
