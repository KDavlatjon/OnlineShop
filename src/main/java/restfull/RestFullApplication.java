package restfull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import restfull.entity.Invoice;
import restfull.repository.InvoiceRepository;

import java.util.List;
@EnableScheduling
@SpringBootApplication
public class RestFullApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestFullApplication.class, args);
    }


}
