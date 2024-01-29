package com.oscar.ecommerce.controllers;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.services.AuthService;
import com.oscar.ecommerce.services.CategoryService;
import com.oscar.ecommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/public")
public class PublicController {
    private final ProductService productService;
    private final AuthService authService;
    private final CategoryService categoryService;
    public PublicController(ProductService productService, AuthService authService, CategoryService categoryService) {
        this.productService = productService;
        this.authService = authService;
        this.categoryService = categoryService;
    }

    @GetMapping("/user")
    public void createUser(@RequestHeader("Authorization") String accessToken) throws Auth0Exception {
        UserInfo userInfo = authService.getUserInfo(accessToken);
        authService.createUserFromUserInfo(userInfo);
    }

    @GetMapping("/products")
    public Page<Product> getProducts(@PageableDefault(sort = "price") Pageable pageable) {
        return productService.getProducts(pageable);
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable long id) { return productService.findProductById(id);}

    @GetMapping("/products/search")
    public Page<Product> getProductsByName(@RequestParam(name = "search") String name, Pageable pageable) {
        return productService.findProductByName(name, pageable);
    }
    @GetMapping("/products/category/{category}")
    public Page<Product> getProductsByCategory(@PathVariable Category category, Pageable pageable) {
        return productService.findProductsByCategory(category, pageable);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }
}
