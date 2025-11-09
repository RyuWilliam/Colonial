package com.colonial.domain.service;

import com.colonial.domain.enums.ProductCategory;
import com.colonial.domain.model.Product;
import com.colonial.domain.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByCategory(ProductCategory category) {
        return productRepository.findAllByCategory(category);
    }

    public List<Product> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findProductsWithLowStock(Integer threshold) {
        return productRepository.findProductsWithLowStock(threshold);
    }

    public List<Product> findAvailableProducts() {
        return productRepository.findAvailableProducts();
    }

    public List<Product> findOutOfStockProducts() {
        return productRepository.findOutOfStockProducts();
    }

    public boolean existsById(Integer id) {
        return productRepository.findById(id).isPresent();
    }
}