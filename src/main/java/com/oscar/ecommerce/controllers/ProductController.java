package com.oscar.ecommerce.controllers;

import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getProducts () {
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(required = false) long id) {
        return this.productService.findProductById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        this.productService.addProduct(product);
    }
}
