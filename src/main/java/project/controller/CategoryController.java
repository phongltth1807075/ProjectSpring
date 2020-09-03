package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.model.Category;
import project.service.CategoryService;
import java.util.Optional;

@Controller
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Category>> getList() {
        return new ResponseEntity<>(categoryService.getList(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category saveCategory = categoryService.create(category);
        if (saveCategory != null) {
            return new ResponseEntity<>(saveCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<Category> exitCategory = categoryService.getById(id);
        if (exitCategory.isPresent()) {
            categoryService.delete(exitCategory.get());
            return new ResponseEntity<>("Delete Success!", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Category> getDetail(@PathVariable int id) {
        Optional<Category> category = categoryService.getById(id);
        if (category.isPresent()) {
            Category category1 = category.get();
            return new ResponseEntity<>(category1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Category category) {
        Optional<Category> categoryUpdate = categoryService.getById(id);
        if (categoryUpdate.isPresent()) {
            Category newCategory = categoryUpdate.get();
            newCategory.setCategoryName(category.getCategoryName());
            newCategory.setStatus(category.getStatus());
            newCategory.setDescription(category.getDescription());
            categoryService.update(newCategory);
            return new ResponseEntity<>("Update Success", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
