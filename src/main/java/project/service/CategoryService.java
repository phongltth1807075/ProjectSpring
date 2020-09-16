package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Category;
import project.repository.CategoryRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getList() {
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        category.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        category.setStatus(Category.CategoryStatus.Active);
        return categoryRepository.save(category);
    }

    public Optional<Category> getById(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory;
    }

    public boolean delete(Category category) {
        category.setStatus(Category.CategoryStatus.Deactive);
        category.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        categoryRepository.save(category);
        return true;
    }

    public Category update(Category category) {
        category.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return categoryRepository.save(category);
    }

}
