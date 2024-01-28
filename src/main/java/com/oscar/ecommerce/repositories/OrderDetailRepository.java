package com.oscar.ecommerce.repositories;

import com.oscar.ecommerce.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
