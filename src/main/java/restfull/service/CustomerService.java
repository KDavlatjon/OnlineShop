package restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restfull.entity.Customer;
import restfull.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    Save
    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

//    Find by id
    public Customer getOne(Long id){
        return  customerRepository.findById(id).orElse(null);
    }

//    Find All
    public List<Customer> getList(){
        return customerRepository.findAll();
    }

//    Check Customer
    public Long check(Customer customer){
        Long customerId = null;
        List<Customer> customerList = customerRepository.findAll();
        for (Customer customer1: customerList) {
            if (customer1.getName().equals(customer.getName()) && customer1.getPhone().equals(customer.getPhone())) {
                customerId = customer1.getId();
                return customerId;
            }
        }
        return customerId;
    }

//    Delete Customer
    public void deleteOne(Long id){
        customerRepository.deleteById(id);
    }

}

