package com.oscar.ecommerce.repositories;

import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findProductByCategory(Category category, Pageable pageable);
    Page<Product> findProductByName(String productName, Pageable pageable);
}
