package com.oscar.ecommerce.services;

import com.oscar.ecommerce.DTO.OrderDTO;
import com.oscar.ecommerce.models.Order;
import com.oscar.ecommerce.models.OrderDetail;
import com.oscar.ecommerce.models.SturshkUser;
import com.oscar.ecommerce.repositories.OrderDetailRepository;
import com.oscar.ecommerce.repositories.OrderRepository;
import com.oscar.ecommerce.repositories.SturshkUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final SturshkUserRepository sturshkUserRepository;
    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
                        SturshkUserRepository sturshkUserRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.sturshkUserRepository = sturshkUserRepository;
    }

    public Order addOrder(OrderDTO orderDTO) {
       return this.orderRepository.save(mapDTOToOrder(orderDTO));
    }

    public Order getOrderById(long id ) {
        return this.orderRepository.findById(id).orElse(null);
    }
    public Order updateOrder(long id, OrderDTO orderDTO) {
    return updateDTOToOrder(id, orderDTO);
    }

    public void deleteOrder(long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        optionalOrder.ifPresent(orderRepository::delete);
    }

    private Order mapDTOToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setDateCreated(orderDTO.getDateCreated());
        order.setStatus(orderDTO.getStatus());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        if (orderDTO.getOrderDetailsId() != null) {
            for (Long id: orderDTO.getOrderDetailsId()) {
                Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
                optionalOrderDetail.ifPresent(orderDetailList::add);
            }
        }
        if (orderDTO.getSturshkUserId() != null) {
            Optional<SturshkUser> optionalSturshkUser = sturshkUserRepository.findById(orderDTO.getSturshkUserId());
            optionalSturshkUser.ifPresent(order::setSturshkUser);
        }
        return order;
    }

    private Order updateDTOToOrder(long id, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (orderDTO.getSturshkUserId() != null) {
                Optional<SturshkUser> optionalSturshkUser = sturshkUserRepository.findById(orderDTO.getSturshkUserId());
                optionalSturshkUser.ifPresent(order::setSturshkUser);
            }
            if (orderDTO.getOrderDetailsId() != null) {
                List<OrderDetail> orderDetailList = new ArrayList<>();
                for (Long orderDetailId: orderDTO.getOrderDetailsId()) {
                    Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(orderDetailId);
                    optionalOrderDetail.ifPresent(orderDetailList::add);
                }
            }
            if (orderDTO.getStatus() != null) {
                order.setStatus(orderDTO.getStatus());
            }
            if (orderDTO.getDateCreated() != null) {
                order.setDateCreated(orderDTO.getDateCreated());
            }
            return order;
        }
        return null;
    }
}
