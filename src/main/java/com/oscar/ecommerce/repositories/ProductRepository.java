package com.oscar.ecommerce.repositories;

import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {
    List<Product> findProductByCategory(Category category);
    List<Product> findProductByName(String productName);
}
