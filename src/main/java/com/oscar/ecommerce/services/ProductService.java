package com.oscar.ecommerce.services;

import com.oscar.ecommerce.DTO.ProductDTO;
import com.oscar.ecommerce.models.Category;
import com.oscar.ecommerce.models.Product;
import com.oscar.ecommerce.repositories.CategoryRepository;
import com.oscar.ecommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    public void addProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new RuntimeException("Product can't be null");
        }
        Product product = mapDTOToProduct(productDTO);
        this.productRepository.save(product);
    }
    public void updateProductById(ProductDTO newProduct, long id) {
       Optional<Product> optionalProduct = this.productRepository.findById(id);
       if (optionalProduct.isPresent()) {
           Product product = optionalProduct.get();
           product = mapDTOToProduct(newProduct);
           this.productRepository.save(product);
       }
    }
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    private Product mapDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        if(productDTO.getCategoryId() != null ) {
            Optional<Category> optionalCategory = this.categoryRepository.findById(productDTO.getCategoryId());
            if (optionalCategory.isEmpty()) {
                return product;
            }
            product.setCategory(optionalCategory.get());
        }
        return product;
    }
}
