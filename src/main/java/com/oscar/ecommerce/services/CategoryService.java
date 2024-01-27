package com.oscar.ecommerce.services;

import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        this.categoryRepository.save(category);
    }
    public void deleteCategoryById(long id) {
        this.categoryRepository.deleteById(id);
    }
}
