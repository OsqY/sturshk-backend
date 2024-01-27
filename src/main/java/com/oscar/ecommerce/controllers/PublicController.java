package com.oscar.ecommerce.controllers;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.models.SturshkUser;
import com.oscar.ecommerce.services.AuthService;
import com.oscar.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/public")
public class PublicController {
    private final ProductService productService;
    private final AuthService authService;
    public PublicController(ProductService productService, AuthService authService) {
        this.productService = productService;
        this.authService = authService;
    }

    @GetMapping("/user")
    public SturshkUser getUser(@RequestHeader("Authorization") String accessToken) throws Auth0Exception {
        UserInfo userInfo = (UserInfo) authService.getUserInfo(accessToken);
        return authService.createUserFromUserInfo(userInfo);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        System.out.println(productService.getProducts());
        return productService.getProducts();
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable long id) { return productService.findProductById(id);}

    @GetMapping("/products/search")
    public List<Product> getProductsByName(@RequestParam(name = "search") String name) {
        return productService.findProductByName(name);
    }
    @GetMapping("/products/{category}")
    public List<Product> getProductsByCategory(@PathVariable Category category) {
        return productService.findProductsByCategory(category);
    }
}
