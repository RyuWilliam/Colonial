package com.colonial.persistence.crud;

import com.colonial.domain.enums.ProductCategory;
import com.colonial.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer> {

    // Buscar todos los productos por categoría
    List<ProductEntity> findAllByCategory(ProductCategory category);

    // Buscar todos los productos cuyo nombre contenga una palabra (insensible a mayúsculas/minúsculas)
    List<ProductEntity> findAllByNameContainingIgnoreCase(String name);

    // Buscar producto exacto por nombre (insensible a mayúsculas/minúsculas)
    Optional<ProductEntity> findByNameIgnoreCase(String name);

    // Buscar productos con bajo stock (menores al umbral)
    @Query("SELECT p FROM ProductEntity p WHERE p.stock < :threshold")
    List<ProductEntity> findProductsWithLowStock(@Param("threshold") Integer threshold);

    // Buscar productos disponibles (stock > 0)
    @Query("SELECT p FROM ProductEntity p WHERE p.stock > 0")
    List<ProductEntity> findAvailableProducts();

    // Buscar productos agotados (stock = 0)
    @Query("SELECT p FROM ProductEntity p WHERE p.stock = 0")
    List<ProductEntity> findOutOfStockProducts();
}
