package com.oscar.ecommerce.services;

import com.oscar.ecommerce.models.Cart;
import com.oscar.ecommerce.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addCart(Cart cart) {
        this.cartRepository.save(cart);
    }
    public Cart getCartById(long id) {
        Optional<Cart> optionalCart = this.cartRepository.findById(id);
        return optionalCart.orElse(null);
    }

    public void updateCart(long id, Cart newCart) {
        Optional<Cart> optionalCart = this.cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            if (cart.getProducts() != null) {
                cart.setProducts(newCart.getProducts());
            }
        }
    }
    public void deleteCart(long id) {
        this.cartRepository.deleteById(id);
    }

}
