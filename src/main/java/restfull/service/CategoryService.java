package restfull.service;

import org.springframework.stereotype.Service;
import restfull.entity.Category;
import restfull.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

//    Add Category
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

//    Get One
    public Category getOne(Integer id){
        return categoryRepository.findById(id).orElse(null);
    }

//    Get List
    public List<Category> getList(){
        return categoryRepository.findAll();
    }
}
