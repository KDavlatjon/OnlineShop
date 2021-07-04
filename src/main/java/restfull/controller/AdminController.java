package restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Product;
import restfull.service.*;

@Controller
@RequestMapping("/adminWeb")
public class AdminController {

    private final CustomerService customerService;
    private final ProductService productService;
    private final InvoiceService invoiceService;
    private final PaymentService paymentService;
    private final CategoryService categoryService;

    @Autowired
    public AdminController(CustomerService customerService, ProductService productService, InvoiceService invoiceService, PaymentService paymentService, CategoryService categoryService) {
        this.customerService = customerService;
        this.productService = productService;
        this.invoiceService = invoiceService;
        this.paymentService = paymentService;
        this.categoryService = categoryService;
    }

//    Product Operation ======================================================================
    @GetMapping("/products")
    public String showProducts(Model model){
        model.addAttribute("products", productService.getList());
        return "manageProducts";
    }

    @GetMapping("/formProduct")
    public String showFormProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categoryList", categoryService.getList());
        return "formProduct";
    }

    @GetMapping("/updateProduct/{id}")
    public String showUpdateProduct(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("product", productService.getOne(id));
        model.addAttribute("categoryList", categoryService.getList());
        return "updateProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product){
        productService.addProduct(product);
        return "redirect:/adminWeb/products";
    }

    @GetMapping("/products/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id){
        productService.deleteOne(id);
        return "redirect:/adminWeb/products";
    }

//    Product Category Operation ======================================================================
    @GetMapping("/categories")
    public String showCategory(Model model){
        model.addAttribute("categories", categoryService.getList());
        return "manageCategories";
    }

    @GetMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Integer id){
        categoryService.deleteOne(id);
        return "redirect:/adminWeb/categories";
    }


//    Customer Operation ======================================================================
    @GetMapping("/customers")
    public String showCustomer(Model model){
        model.addAttribute("customers", customerService.getList());
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String deleteCustomers(@PathVariable Long id){
        customerService.deleteOne(id);
        return "redirect:customers";
    }

    //    Invoice Operation ======================================================================
    @GetMapping("/invoice")
    public String showInvoice(Model model){
        model.addAttribute("invoices", invoiceService.InvoiceList());
        return "invoice";
    }

    //    Customer Operation ======================================================================
    @GetMapping("/payment")
    public String showPayment(Model model){
        model.addAttribute("payments", paymentService.PaymentList());
        return "payment";
    }

}
