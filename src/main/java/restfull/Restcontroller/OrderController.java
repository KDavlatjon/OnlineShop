package restfull.Restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Orders;
import restfull.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrdersService ordersService;


    @Autowired
    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/add/{customerId}")
    public Orders addOrder(@PathVariable(name = "customerId") Long customerId){
        return ordersService.addOrder(customerId);
    }

    @GetMapping("/get/{id}")
    public Orders getOne(@PathVariable(name = "id") Long id){
        System.out.println("id="+id);
        return ordersService.getOne(id);
    }

    @GetMapping("/list")
    public List<Orders> getList(){
        return ordersService.getList();
    }



}
