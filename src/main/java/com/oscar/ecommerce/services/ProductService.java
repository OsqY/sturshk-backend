package com.oscar.ecommerce.services;

import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findProductById(long id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElse(null);
    }

    public Page<Product> findProductByName(String name, Pageable pageable) {
        return this.productRepository.findProductByName(name, pageable);

    }
    public Page<Product> findProductsByCategory(Category category, Pageable pageable) {
        return this.productRepository.findProductByCategory(category, pageable);
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new RuntimeException("Product can't be null");
        }
        this.productRepository.save(product);
    }
    public void updateProductById(Product newProduct, long id) {
       Optional<Product> optionalProduct = this.productRepository.findById(id);
       if (optionalProduct.isPresent()) {
           Product product = optionalProduct.get();
           product.setDescription(newProduct.getDescription());
           product.setName(newProduct.getName());
           product.setPrice(newProduct.getPrice());
           this.productRepository.save(product);
       }
    }
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

}
