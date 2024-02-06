package com.oscar.ecommerce.controllers;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.oscar.ecommerce.DTO.CartDTO;
import com.oscar.ecommerce.models.Cart;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.services.AuthService;
import com.oscar.ecommerce.services.CartService;
import com.oscar.ecommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/public")
public class PublicController {
    private final ProductService productService;
    private final AuthService authService;
    private final CartService cartService;

    public PublicController(ProductService productService, AuthService authService, CartService cartService) {
        this.productService = productService;
        this.authService = authService;
        this.cartService = cartService;
    }

    @GetMapping("/user")
    public void createUser(@RequestHeader("Authorization") String accessToken) throws Auth0Exception {
        UserInfo userInfo = authService.getUserInfo(accessToken);
        authService.createUserFromUserInfo(userInfo);
    }

    @GetMapping("/products")
    public Page<Product> getProducts(@PageableDefault(sort = "price", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.getProducts(pageable);
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable long id) { return productService.findProductById(id);}

    @GetMapping("/products/search")
    public Page<Product> getProductsByName(@RequestParam(name = "search") String name, Pageable pageable) {
        return productService.findProductByName(name, pageable);
    }
    @GetMapping("/products/category/{category}")
    public Page<Product> getProductsByCategoryName(@PathVariable String categoryName, Pageable pageable) {
        return productService.findProductsByCategory(categoryName, pageable);
    }

    @PostMapping("/cart")
    public ResponseEntity<?> createCart(@RequestBody CartDTO cartDTO) {
        Cart cart = this.cartService.addCart(cartDTO);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<?> updateCart(@PathVariable long id, @RequestBody CartDTO cartDTO) {
        Cart cart = this.cartService.updateCart(id, cartDTO);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable long id) {
        this.cartService.deleteCart(id);
        return new ResponseEntity<>("Cart with id: ${id} was deleted", HttpStatus.OK);
    }
}
