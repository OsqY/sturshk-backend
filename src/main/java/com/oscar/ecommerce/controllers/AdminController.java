package com.oscar.ecommerce.controllers;

import com.oscar.ecommerce.DTO.CategoryDTO;
import com.oscar.ecommerce.DTO.ProductDTO;
import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.services.CategoryService;
import com.oscar.ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = this.productService.addProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductDTO newProduct) {
      Product product = this.productService.updateProductById(newProduct, id);
      return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        this.productService.deleteProductById(id);
        return new ResponseEntity<>("Product with id: ${id} was deleted", HttpStatus.OK);
    }

    @PostMapping("/category")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = this.categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
        Category category = this.categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {
        this.categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("Category with id: ${id} was deleted", HttpStatus.OK);
    }

}
