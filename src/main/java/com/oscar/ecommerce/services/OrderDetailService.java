package com.oscar.ecommerce.services;

import com.oscar.ecommerce.DTO.OrderDetailDTO;
import com.oscar.ecommerce.models.Order;
import com.oscar.ecommerce.models.OrderDetail;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.repositories.OrderDetailRepository;
import com.oscar.ecommerce.repositories.OrderRepository;
import com.oscar.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderDetail getOrderById(long id) {
        return this.orderDetailRepository.findById(id).orElse(null);
    }
    public OrderDetail addOrderDetail(OrderDetailDTO orderDetailDTO) {
        return mapDTOToOrder(orderDetailDTO);
    }
    public OrderDetail updateOrderDetail(long id, OrderDetailDTO orderDetailDTO) {
        return updateDTOToOrderDetail(id, orderDetailDTO);
    }
    public void deleteOrderDetail(long id) {
        this.orderDetailRepository.deleteById(id);
    }

    private OrderDetail mapDTOToOrder(OrderDetailDTO orderDetailDTO) {
        if (orderDetailDTO == null) {
            throw new RuntimeException("OrderDetail can't be null");
        }
        OrderDetail orderDetail = new OrderDetail();
        if (orderDetailDTO.getOrderId() != null) {
            Optional<Order> optionalOrderDetail = this.orderRepository.findById(orderDetailDTO.getOrderId());
            optionalOrderDetail.ifPresent(orderDetail::setOrder);
        }
        if (orderDetailDTO.getProductId() != null) {
            Optional<Product> productOptional = this.productRepository.findById(orderDetailDTO.getProductId());
            productOptional.ifPresent(orderDetail::setProduct);
        }
        if (orderDetailDTO.getQuantity() != null) {
            orderDetail.setQuantity(orderDetailDTO.getQuantity());
        }
        return orderDetail;
    }

    private OrderDetail updateDTOToOrderDetail(long id, OrderDetailDTO orderDetailDTO) {
        Optional<OrderDetail> optionalOrderDetail = this.orderDetailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            if (orderDetailDTO.getQuantity() != null) {
                orderDetail.setQuantity(orderDetail.getQuantity());
            }
            if (orderDetailDTO.getProductId() != null) {
                Optional<Product> optionalProduct = this.productRepository.findById(orderDetailDTO.getProductId());
                optionalProduct.ifPresent(orderDetail::setProduct);
            }
            if (orderDetailDTO.getOrderId() != null) {
                Optional<Order> optionalOrder = this.orderRepository.findById(orderDetailDTO.getOrderId());
                optionalOrder.ifPresent(orderDetail::setOrder);
            }
            return orderDetail;
        }
        return null;
    }
}
