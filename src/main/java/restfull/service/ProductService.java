package restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restfull.entity.Product;
import restfull.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//  Add
    public Product addProduct(Product product) {
     return productRepository.save(product);
    }

//  Get one
    public Product getOne(Long id){
        if (id==null)return null;
        return productRepository.findById(id).orElse(null);
    }

//  GetList
    public List<Product> getList(){
        return productRepository.findAll();
    }

//    Delete One
    public void deleteOne(Long id){
        productRepository.deleteById(id);
    }
}
