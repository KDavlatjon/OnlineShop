package restfull.Restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import restfull.entity.Category;
import restfull.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@Api(tags = "Category malumotlari")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @ApiOperation(tags = "Hello")
    @PostMapping("/add")
    public Category add(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("get/{id}")
    public Category getOne(@PathVariable(name = "id") Integer id){
        return categoryService.getOne(id);
    }

    @GetMapping("/list")
    public List<Category> getList(){
        return categoryService.getList();
    }

}