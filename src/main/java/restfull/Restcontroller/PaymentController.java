package restfull.Restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Payment;
import restfull.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public Payment add(
            @RequestParam(name = "invoiceId") Long invoiceId,
            @RequestParam(name = "paymentAmount") Integer amount
    ){
        return paymentService.add(invoiceId, amount);
    }

    @GetMapping("/get/{id}")
    public Payment getOne(@PathVariable(name = "id") Long id){
        return paymentService.getOne(id);
    }

    @GetMapping("/list")
    public List<Payment> PaymentList(){
        return paymentService.PaymentList();
    }
}
