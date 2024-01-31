package com.oscar.ecommerce.services;

import com.oscar.ecommerce.DTO.CategoryDTO;
import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.repositories.CategoryRepository;
import com.oscar.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    public Category addCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new RuntimeException("Category can't be null");
        }
        return this.categoryRepository.save(mapDTOToCategory(categoryDTO));
    }
    public Category getCategoryById(long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }
    public Category updateCategory(long id, CategoryDTO newCategory) {
        return updateDTOToCategory(id, newCategory);
    }

    public void deleteCategoryById(long id) {
        this.categoryRepository.deleteById(id);
    }

    private Category mapDTOToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        List<Product> products = new ArrayList<>();
        if (categoryDTO.getProductIds() != null) {
            for (Long id: categoryDTO.getProductIds()) {
                Optional<Product> optionalProduct = this.productRepository.findById(id);
                optionalProduct.ifPresent(products::add);
            }
        }
        category.setProducts(products);
        return category;
    }

    private Category updateDTOToCategory(long id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            if (categoryDTO.getProductIds() != null) {
                List<Product> productsList = new ArrayList<>();
                for (Long productId: categoryDTO.getProductIds()) {
                    Optional<Product> optionalProduct = this.productRepository.findById(productId);
                    optionalProduct.ifPresent(productsList::add);
                }
                category.setProducts(productsList);
            }
            if (categoryDTO.getName() != null) {
                category.setName(categoryDTO.getName());
            }
            return category;
        }
        return null;
    }
}
