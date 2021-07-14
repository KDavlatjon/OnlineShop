package restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restfull.entity.Orders;
import restfull.repository.OrdersRepository;

import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

//    Add
    public Orders addOrder(Long userId){
        Orders orders = new Orders();
        orders.setUserId(userId);
        return ordersRepository.save(orders);
    }

//  Get One
    public Orders getOne(Long id){
        return ordersRepository.findById(id).orElse(null);
    }

//    Get list
    public List<Orders> getList(){
        return ordersRepository.findAll();
    }


}
