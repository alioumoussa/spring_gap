package th.ac.ku.atm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import th.ac.ku.atm.model.Machine;

@Controller
@RequestMapping("/")
public class startController {
        @GetMapping("")
    public String getAcceuil() {
    //    List<Machine> all= repository.findAll();
        return "WEB-INF/start";
    }
}
