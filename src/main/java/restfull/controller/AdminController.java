package restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Category;
import restfull.entity.Product;
import restfull.service.*;

@Controller
@RequestMapping("/adminWeb")
public class AdminController {

    private final UserService userService;
    private final ProductService productService;
    private final InvoiceService invoiceService;
    private final PaymentService paymentService;
    private final CategoryService categoryService;

    @Autowired
    public AdminController(UserService userService, ProductService productService, InvoiceService invoiceService, PaymentService paymentService, CategoryService categoryService) {
        this.userService = userService;
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
    @GetMapping("/formCategory")
    public String showFormCategory(Model model){
        model.addAttribute("category", new Category());
        return "formCategory";
    }

    @GetMapping("/updateCategory/{id}")
    public String showUpdateCategory(@PathVariable(value = "id") Integer id, Model model){
        model.addAttribute("category", categoryService.getOne(id));
        return "updateCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category){
        categoryService.addCategory(category);
        return "redirect:/adminWeb/categories";
    }

    @GetMapping("/category/{id}")
    public String deleteCategory(@PathVariable Integer id){
        categoryService.deleteOne(id);
        return "redirect:/adminWeb/categories";
    }


//    Users Operation ======================================================================
    @GetMapping("/customers")
    public String showCustomer(Model model){
        model.addAttribute("customers", userService.getList());
        return "manageCustomers";
    }

    @GetMapping("/customers/{id}")
    public String deleteCustomers(@PathVariable Long id){
        userService.deleteOne(id);
        return "redirect:/adminWeb/customers";
    }

    //    Invoice Operation ======================================================================
    @GetMapping("/invoice")
    public String showInvoice(Model model){
        model.addAttribute("invoices", invoiceService.InvoiceList());
        return "invoice";
    }

    //    Users Operation ======================================================================
    @GetMapping("/payment")
    public String showPayment(Model model){
        model.addAttribute("payments", paymentService.PaymentList());
        return "payment";
    }

}
