package restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restfull.entity.Invoice;
import restfull.entity.Payment;
import restfull.repository.InvoiceRepository;
import restfull.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

//    Add
    public Payment add(Long invoiceId){
        Payment payment = new Payment();
        Invoice invoice = invoiceRepository.findById(invoiceId).orElse(null);
        payment.setInvoice(invoice);
        payment.setAmount(invoice.getAmount());

        return paymentRepository.save(payment);
    }

//    Get One
    public Payment getOne(Long id){
        if (id==null)return null;
        return paymentRepository.findById(id).orElse(null);
    }

//    Get List
    public List<Payment> PaymentList(){
        return paymentRepository.findAll();
    }
}
