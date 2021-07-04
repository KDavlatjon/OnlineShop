package restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restfull.entity.Details;
import restfull.repository.DetailsRepository;
import restfull.repository.OrdersRepository;
import restfull.repository.ProductRepository;

import java.util.List;

@Service
public class DetailsService {

    private final DetailsRepository detailsRepository;
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DetailsService(DetailsRepository detailsRepository, OrdersRepository ordersRepository, ProductService productService, ProductRepository productRepository) {
        this.detailsRepository = detailsRepository;
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }

    //    Add
    public Details addDetails(Long productId, Long orderId, Integer quantity){
        Details details = new Details();
        details.setOrders(ordersRepository.findById(orderId).orElse(null));
        details.setProduct(productRepository.findById(productId).orElse(null));
        details.setQuantity(quantity);
        return detailsRepository.save(details);
    }
    //    Get Amount
    public Integer getAmount(Long orderId){
        Integer amount =0;
        List<Details> detailsList = detailsRepository.findDetailsListByOrderId(orderId);
        for (Details details : detailsList) {
            amount = amount + details.getQuantity()*details.getProduct().getPrice();
        }
        return amount;
    }

    //  Get One
    public Details getOne(Long id){
        if (id == null)return null;
        return detailsRepository.findById(id).orElse(null);
    }

    //    Get list
    public List<Details> getList(){
        return detailsRepository.findAll();
    }

    //    Get Details lList by Order Id
    public List<Details> getInvoiceDetails(Long orderId){
        List<Details> detailsList = detailsRepository.findDetailsListByOrderId(orderId);
        return detailsList;
    }

//    Delete Details
    public void deleteDetails(Long id){
        detailsRepository.deleteById(id);
    }

}
