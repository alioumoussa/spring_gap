package th.ac.ku.atm.controller.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.service.BankAccountService;
import th.ac.ku.atm.service.CustomerService;

@Controller
@RequestMapping("/login")

public class LoginController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private BankAccountService bankAccountService;

    public LoginController(CustomerService customerService, BankAccountService bankAccountService) {
        this.customerService = customerService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute Customer customer, Model model){
        // 1. Check to see if id and pin matched customer info
        // 3. not match, display that customer info is incorrect
        Customer matchingCustomer = customerService.checkPin(customer);

        // 2. if match, welcome customer
        if (matchingCustomer != null) {
            model.addAttribute("customertitle",
                    matchingCustomer.getName() + " Bank Account");
            model.addAttribute("bankaccounts",
                    bankAccountService.getCustomerBankAccount(customer.getId()));
            return "customeraccount";
        } else {
            model.addAttribute("greeting", "Can't find customer");
            return "home";
        }
    }
}
