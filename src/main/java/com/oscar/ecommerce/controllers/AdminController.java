package com.oscar.ecommerce.controllers;

import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.services.CategoryService;
import com.oscar.ecommerce.services.ProductService;
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

    @PostMapping("/categories")
    public void addCategory(@RequestBody Category category) {
        this.categoryService.addCategory(category);
    }
}
