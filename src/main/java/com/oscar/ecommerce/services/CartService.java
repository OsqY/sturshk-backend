package com.oscar.ecommerce.services;

import com.oscar.ecommerce.DTO.CartDTO;
import com.oscar.ecommerce.models.Cart;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.models.SturshkUser;
import com.oscar.ecommerce.repositories.CartRepository;
import com.oscar.ecommerce.repositories.ProductRepository;
import com.oscar.ecommerce.repositories.SturshkUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final SturshkUserRepository sturshkUserRepository;
    private final ProductRepository productRepository;
    public CartService(CartRepository cartRepository, SturshkUserRepository sturshkUserRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.sturshkUserRepository = sturshkUserRepository;
        this.productRepository = productRepository;
    }
    public Cart addCart(CartDTO cartDTO) {
        return mapDTOToCart(cartDTO);
    }
    public Cart getCartById(long id) {
        Optional<Cart> optionalCart = this.cartRepository.findById(id);
        return optionalCart.orElse(null);
    }

    public  Cart updateCart(long id, CartDTO newCart) {
        return updateDTOToCart(id, newCart);
    }
    public void deleteCart(long id) {
        this.cartRepository.deleteById(id);
    }

    private Cart mapDTOToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        if (cartDTO.getSturkUserId() != null) {
            Optional<SturshkUser> sturshkUser = sturshkUserRepository.findById(cartDTO.getSturkUserId());
            sturshkUser.ifPresent(cart::setSturshkUser);
        }
        if (cartDTO.getProductsId() != null) {
            List<Product> productList = new ArrayList<>();
            for (long id: cartDTO.getProductsId()) {
            Optional<Product> optionalProduct = this.productRepository.findById(id);
            optionalProduct.ifPresent(productList::add);
            }
            if(!productList.isEmpty()) {
                cart.setProducts(productList);
            }
        }
        return cart;
    }

    private Cart updateDTOToCart(long id, CartDTO cartDTO) {
        Optional<Cart> optionalCart = this.cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            if (cartDTO.getProductsId() != null) {
                List<Product> productList = new ArrayList<>();
                for (Long productId: cartDTO.getProductsId()) {
                    Optional<Product> optionalProduct = this.productRepository.findById(productId);
                    optionalProduct.ifPresent(productList::add);
                }
                cart.setProducts(productList);
            }
            if (cartDTO.getSturkUserId() != null) {
                Optional<SturshkUser> optionalSturshkUser = this.sturshkUserRepository.findById(cartDTO.getSturkUserId());
                optionalSturshkUser.ifPresent(cart::setSturshkUser);
            }
            return cart;
        }
        return null;
    }
}
