package restfull.Restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Customer;
import restfull.entity.Orders;
import restfull.service.CustomerService;
import restfull.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private  final CustomerService customerService;
    private final OrdersService ordersService;

    @Autowired
    public CustomerController(CustomerService customerService, OrdersService ordersService) {
        this.customerService = customerService;
        this.ordersService = ordersService;
    }

    @PostMapping("/add")
    public Customer add(@RequestBody Customer customer){
       return customerService.addCustomer(customer);
    }

    @GetMapping("/get/{id}")
    public Customer getOne(@PathVariable(name = "id") Long id){
        return customerService.getOne(id);
    }

    @GetMapping("/list")
    public List<Customer> CustomerList(){
        return customerService.getList();
    }

    @PutMapping("/{customerId}/orders/{ordersId}")
    public Customer customerToOrders(
            @PathVariable Long customerId,
            @PathVariable Long ordersId
    ){
        Customer customer = customerService.getOne(customerId);
        Orders orders = ordersService.getOne(ordersId);
//        customer.putOrders(orders);
        return customerService.addCustomer(customer);

    }
}
