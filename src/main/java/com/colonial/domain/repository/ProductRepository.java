package com.colonial.domain.repository;

import com.colonial.domain.enums.ProductCategory;
import com.colonial.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);
    Optional<Product> findById(Integer id);
    List<Product> findAll();
    void deleteById(Integer id);

    List<Product> findAllByCategory(ProductCategory category);
    List<Product> findAllByName(String name);
    Optional<Product> findByName(String name);
    List<Product> findProductsWithLowStock(Integer threshold);
    List<Product> findAvailableProducts();
    List<Product> findOutOfStockProducts();
}