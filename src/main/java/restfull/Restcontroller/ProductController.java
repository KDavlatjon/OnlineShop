package restfull.Restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Product;
import restfull.service.DetailsService;
import restfull.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class    ProductController {

    private final ProductService productService;
    private final DetailsService detailsService;

    @Autowired
    public ProductController(ProductService productService, DetailsService detailsService) {
        this.productService = productService;
        this.detailsService = detailsService;
    }

    @PostMapping("/add")
    public Product add(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/get/{id}")
    public Product getOne(@PathVariable(name = "id") Long id){
        return productService.getOne(id);
    }

    @GetMapping("/list")
    public List<Product> getList(){
        return productService.getList();
    }

//    Create Order
//    @PutMapping("/{productId}/products/detailsId}")
//    public Product detailsToProduct(
//            @PathVariable Long productId,
//            @PathVariable Long detailsId
//    ){
//        Product product = productService.getOne(productId);
//        Details details = detailsService.getOne(detailsId);
//        product.putDetails(details);
//        return productService.addProduct(product);
//    }
}
