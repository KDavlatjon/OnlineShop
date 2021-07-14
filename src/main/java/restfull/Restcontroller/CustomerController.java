package restfull.Restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Users;
import restfull.service.UserService;
import restfull.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private  final UserService userService;
    private final OrdersService ordersService;

    @Autowired
    public CustomerController(UserService userService, OrdersService ordersService) {
        this.userService = userService;
        this.ordersService = ordersService;
    }

    @PostMapping("/add")
    public Users add(@RequestBody Users users){
       return userService.addCustomer(users);
    }

    @GetMapping("/get/{id}")
    public Users getOne(@PathVariable(name = "id") Long id){
        return userService.getOne(id);
    }

    @GetMapping("/list")
    public List<Users> CustomerList(){
        return userService.getList();
    }


}
