package restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import restfull.entity.Invoice;
import restfull.repository.InvoiceRepository;
import restfull.repository.PaymentRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
    }

//    Add
    public Invoice add(Long orderId, Integer amount){

        Invoice invoice = new Invoice();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using today's date
        c.add(Calendar.DATE, 5); // Adding 5 days
        invoice.setOrdersId(orderId);
        invoice.setAmount(amount);
        invoice.setDue(c.getTime());

        return invoiceRepository.save(invoice);
    }

//    Get One
    public Invoice getOne(Long id){
        return invoiceRepository.findById(id).orElse(null);
    }

//    Get List
    public List<Invoice> InvoiceList(){
        return invoiceRepository.findAll();
    }

//    Delete Overdue Invoices
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteInvoice(){
        Date date = new Date();
        List<Invoice> invoiceList = invoiceRepository.findAll();
        for (Invoice invoice : invoiceList) {
//            5 day
            if (((invoice.getDue().getTime()-date.getTime())<1) && paymentRepository.findByInvoice(invoice)==null){
                invoiceRepository.delete(invoice);
            }
        }
    }

}
