package com.oscar.ecommerce.controllers;

import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.services.CategoryService;
import com.oscar.ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ProductService productService;
    private final CategoryService categoryService;
    public AdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @PostMapping("/products")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product) {
        this.productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product newProduct) {
      this.productService.updateProductById(newProduct, id);

    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        this.productService.deleteProductById(id);
    }

    @PostMapping("/category")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addCategory(@RequestBody Category category) {
        this.categoryService.addCategory(category);
    }

    @PutMapping("/category/{id}")
    public void updateCategory(@PathVariable long id, @RequestBody Category category) {
        this.categoryService.updateCategory(id, category);
    }
    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable long id) {
        this.categoryService.deleteCategoryById(id);
    }
}
