package restfull.Restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Details;
import restfull.service.DetailsService;

import java.util.List;

@RestController
@RequestMapping("/details")
public class DetailsController {

    private final DetailsService detailsService;

    @Autowired
    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @PostMapping("/add/{productId}/{orderId}/{quantity}")
    public Details add(@PathVariable Long productId, Long orderId, Integer quantity){
        return detailsService.addDetails(productId, orderId, quantity);
    }

    @GetMapping("/get/{id}")
    public Details getOne(@PathVariable(name = "id") Long id){
        return detailsService.getOne(id);
    }

    @GetMapping("/list")
    public List<Details> detailsList(){
        return detailsService.getList();
    }
}
