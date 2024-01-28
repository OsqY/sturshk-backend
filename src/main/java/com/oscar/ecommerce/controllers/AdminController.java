package com.oscar.ecommerce.controllers;

import com.oscar.ecommerce.DTO.CategoryDTO;
import com.oscar.ecommerce.DTO.ProductDTO;
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
    public void addProduct(@RequestBody ProductDTO productDTO) {
        this.productService.addProduct(productDTO);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody ProductDTO newProduct) {
      this.productService.updateProductById(newProduct, id);

    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        this.productService.deleteProductById(id);
    }

    @PostMapping("/category")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addCategory(@RequestBody CategoryDTO categoryDTO) {
        this.categoryService.addCategory(categoryDTO);
    }

    @PutMapping("/category/{id}")
    public void updateCategory(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
        this.categoryService.updateCategory(id, categoryDTO);
    }
    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable long id) {
        this.categoryService.deleteCategoryById(id);
    }
}
