package com.colonial.persistence.crud;

import com.colonial.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByCategory(String category);
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
