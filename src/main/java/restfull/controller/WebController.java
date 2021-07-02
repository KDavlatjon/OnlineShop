package restfull.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import restfull.entity.Customer;
import restfull.entity.Details;


@Controller
@RequestMapping("/web")
public class WebController {

    @GetMapping( "/signIn")
    public String showSignIn(Model model){
        model.addAttribute("customer", new Customer());
        return "signIn";
    }

    @GetMapping("/signUp")
    public String showSignUp(Model model){
        model.addAttribute("customer", new Customer());
        return "signUp";
    }

    @GetMapping("/cart")
    public String showCart(){
        return "cart";
    }

    @GetMapping("/invoice")
    public String showInvoice(){
        return "invoice";
    }

    @GetMapping("/payment")
    public String showPayment(){
        return "payment";
    }


}
