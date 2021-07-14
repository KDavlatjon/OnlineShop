package restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import restfull.entity.LoginVM;
import restfull.entity.Users;
import restfull.security.JwtTokenProvider;
import restfull.service.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/customerWeb")
public class CustomerWebController {

    private final UserService userService;
    private final OrdersService ordersService;
    private final ProductService productService;
    private final DetailsService detailsService;
    private final InvoiceService invoiceService;
    private final PaymentService paymentService;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public CustomerWebController(UserService userService, OrdersService ordersService, ProductService productService, DetailsService detailsService, InvoiceService invoiceService, PaymentService paymentService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.ordersService = ordersService;
        this.productService = productService;
        this.detailsService = detailsService;
        this.invoiceService = invoiceService;
        this.paymentService = paymentService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }


//  Show SignIn
    @GetMapping( "/signIn")
    public String showSignIn(Model model){
        model.addAttribute("loginVM", new LoginVM());
        return "signIn";
    }

//  Show SignUp
    @GetMapping( "/signUp")
    public String showSignUp(Model model){
        model.addAttribute("customer", new Users());
        return "signUp";
    }

//  Add new Users from SignUp form
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Users users, Model model){
        if(!userService.checkPasswordLength(users.getPassword())){
            model.addAttribute("message", "Parol o'lchami 4 dan kam bo'lmasligi kerak.");
            return "redirect:signUp";
        }
        if (userService.checkUserPhone(users.getPhone())){
            model.addAttribute("message", "Bu raqam bilan ro'yxatdan o'tilgan.");
            return "redirect:signUp";
        }
        userService.addCustomer(users);
        return  "redirect:signIn";
    }

//  Authentication
    @GetMapping("/check")
    public String login(@ModelAttribute LoginVM loginVM, Model model){
        Users users = userService.getByName(loginVM.getName());
        if (users == null){
            model.addAttribute("message", "Bunday foydalanuvchi mavjud emas.");
            return "redirect:signIn";
        }
        String token = jwtTokenProvider.createToken(users.getName(), users.getRoles());

        Map<Object, Object> map = new HashMap<>();
        map.put("UserName", users.getName());
        map.put("token", token);
        map.put("userId", users.getId());
        Long userId = users.getId();
        model.addAttribute("productList", productService.getList());
        model.addAttribute("ordersId",ordersService.addOrder(userId).getId());
        return "listProduct";
    }
//    @GetMapping("/check")
//    public String checkCustomer(
//            @ModelAttribute Users users,
//            Model model
//            ){
//        Long customerId= userService.check(users);
//        if (customerId != null){
//            model.addAttribute("productList", productService.getList());
//            model.addAttribute("ordersId",ordersService.addOrder(customerId).getId());
//            return "listProduct";
//        }
//        return "redirect:/web/signIn";
//    }

//   Show Cart
    @GetMapping("/cart/{ordersId}")
    public String showCart(
            @PathVariable(name = "ordersId") Long ordersId,
            Model model
            ){
        model.addAttribute("ordersId", ordersId);
        model.addAttribute("detailsList", detailsService.getInvoiceDetails(ordersId));
        model.addAttribute("totalAmount",detailsService.getAmount(ordersId));
        return "cart";
    }

//  Delete Product in order
    @PostMapping("/delete/{detailId}")
    public String deleteDetail(
            @PathVariable Long detailId,
            @RequestParam Long ordersId,
            Model model
            ){
        detailsService.deleteDetails(detailId);
        model.addAttribute("ordersId", ordersId);
        model.addAttribute("detailsList",detailsService.getInvoiceDetails(ordersId));
        model.addAttribute("totalAmount",detailsService.getAmount(ordersId));
        return "cart";
    }

//  Create Invoice
    @GetMapping("/invoice/{ordersId}")
    public String showInvoice(

            @PathVariable Long ordersId,
            Model model
            ){
        model.addAttribute("invoice", invoiceService.add(ordersId, detailsService.getAmount(ordersId)));
        model.addAttribute("detailsList", detailsService.getInvoiceDetails(ordersId));
        model.addAttribute("totalAmount", detailsService.getAmount(ordersId));
        return "invoice";
    }

//  Create Payment
    @GetMapping("/payment/{invoiceId}")
    public String showPayment(
            @PathVariable Long invoiceId,
            Model model
            ){
        model.addAttribute("payment", paymentService.add(invoiceId));
        return "payment";
    }


//  Add product to order
    @PostMapping ("/add_details")
    public String addDetails(
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "ordersId") Long ordersId,
            @RequestParam(name = "quantity") Integer quantity,
            Model model
            ){
        detailsService.addDetails(productId, ordersId, quantity);
        model.addAttribute("ordersId", ordersId);
        model.addAttribute("productList",productService.getList());
        return "listProduct";
    }

//  Show Product List
    @GetMapping("/listProduct/{ordersId}")
    public String showListProduct(
            @PathVariable(name = "ordersId") Long ordersId,
            Model model
            ){
        model.addAttribute("ordersId", ordersId);
        model.addAttribute("productList",productService.getList());
        return "listProduct";
    }

}
