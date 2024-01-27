package com.oscar.ecommerce.services;

import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new RuntimeException("Product can't be null");
        }
        this.productRepository.save(product);
    }
    public Product findProductById(long id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElse(null);
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
