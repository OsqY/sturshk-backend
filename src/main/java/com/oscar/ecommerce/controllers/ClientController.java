package com.oscar.ecommerce.controllers;


import com.oscar.ecommerce.models.Cart;
import com.oscar.ecommerce.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final CartService cartService;

    public ClientController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCart(@RequestBody Cart cart) {
        this.cartService.addCart(cart);
    }

    @PutMapping("/cart/{id}")
    public void updateCart(@PathVariable long id, @RequestBody Cart cart) {
        this.cartService.updateCart(id, cart);
    }
    @DeleteMapping("/cart/{id}")
    public void deleteCart(@PathVariable long id) {
        this.cartService.deleteCart(id);
    }
}
