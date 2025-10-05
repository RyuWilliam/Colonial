package com.colonial.domain.repository;

import com.colonial.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    Product saveProduct(Product product);
    Product getById(int id);
    List<Product> getAll();
    List<Product> getAllByCategory(String category);
    List<Product> getByName(String name);
}
