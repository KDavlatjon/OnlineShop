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

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category getOne(Integer id){
        if (id == null)return null;
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> getList(){
        return categoryRepository.findAll();
    }
}
