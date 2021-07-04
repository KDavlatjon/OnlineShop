package restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Customer;
import restfull.service.*;

@Controller
@RequestMapping("/customerWeb")
public class CustomerWebController {

    private final CustomerService customerService;
    private final OrdersService ordersService;
    private final ProductService productService;
    private final DetailsService detailsService;
    private final InvoiceService invoiceService;
    private final PaymentService paymentService;

    @Autowired
    public CustomerWebController(CustomerService customerService, OrdersService ordersService, ProductService productService, DetailsService detailsService, InvoiceService invoiceService, PaymentService paymentService) {
        this.customerService = customerService;
        this.ordersService = ordersService;
        this.productService = productService;
        this.detailsService = detailsService;
        this.invoiceService = invoiceService;
        this.paymentService = paymentService;
    }
//  Show SignIn
    @GetMapping( "/signIn")
    public String showSignIn(Model model){
        model.addAttribute("customer", new Customer());
        return "signIn";
    }

//  Show SignUp
    @GetMapping( "/signUp")
    public String showSignUp(Model model){
        model.addAttribute("customer", new Customer());
        return "signUp";
    }

//  Add new Customer from SignUp form
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer){
        customerService.addCustomer(customer);
        return  "redirect:signIn";
    }

//  Authentication
    @GetMapping("/check")
    public String checkCustomer(
            @ModelAttribute Customer customer,
            Model model
            ){
        Long customerId= customerService.check(customer);
        if (customerId != null){
            model.addAttribute("productList", productService.getList());
            model.addAttribute("ordersId",ordersService.addOrder(customerId).getId());
            return "listProduct";
        }
        return "redirect:/web/signIn";
    }

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
