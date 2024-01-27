package com.oscar.ecommerce.controllers;

import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ProductService productService;
    public AdminController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        this.productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product newProduct) {
      this.productService.updateProductById(newProduct, id);

    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        this.productService.deleteProductById(id);
    }
}
