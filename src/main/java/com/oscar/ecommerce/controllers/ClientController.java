package com.oscar.ecommerce.controllers;


import com.oscar.ecommerce.DTO.OrderDTO;
import com.oscar.ecommerce.models.Cart;
import com.oscar.ecommerce.models.Order;
import com.oscar.ecommerce.services.CartService;
import com.oscar.ecommerce.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final CartService cartService;
    private final OrderService orderService;

    public ClientController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
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

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/order")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> addOrder(OrderDTO orderDTO) {
        Order createdOrder = this.orderService.addOrder(orderDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrderById(@PathVariable long id, @RequestBody OrderDTO orderDTO) {
        Order order = orderService.updateOrder(id, orderDTO);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long id, @RequestBody OrderDTO orderDTO) {
        this.orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
