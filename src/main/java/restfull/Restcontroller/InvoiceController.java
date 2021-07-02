package restfull.Restcontroller;

import org.springframework.web.bind.annotation.*;
import restfull.entity.Invoice;
import restfull.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    //    Add
    @PostMapping("/add/{orderId}")
    public Invoice add(
            @PathVariable(name = "orderId") Long orderId,
            @RequestParam(name = "amount") Integer amount
            ){
        return invoiceService.add(orderId, amount);
    }

    //    Get One
    @GetMapping("/get/{id}")
    public Invoice getOne(@PathVariable(name = "id") Long id){
        return invoiceService.getOne(id);
    }

    //    Get List
    @GetMapping("/list")
    public List<Invoice> InvoiceList(){
        return invoiceService.InvoiceList();
    }
}
