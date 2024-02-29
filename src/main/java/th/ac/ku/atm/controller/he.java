package th.ac.ku.atm.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 

@Controller
public class he{
    

    @GetMapping("/hello")
    public String hello(Model model) {
        // Add a "name" attribute to the model
        model.addAttribute("name", "World");
        // Return the name of the Thymeleaf template (without the ".html" extension)
        return "hello";
    }
}