package com.colonial.unitaries;

import com.colonial.domain.enums.ProductCategory;
import com.colonial.domain.model.Product;
import com.colonial.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductRepositoryTest {

    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ProductRepository.class);
    }

    @Test
    void testSave() {
        Product product = new Product(1, "Arroz", 3000.0, 50, ProductCategory.GROCERY, LocalDateTime.now());

        when(repository.save(product)).thenReturn(product);

        Product saved = repository.save(product);

        assertNotNull(saved);
        assertEquals("Arroz", saved.getName());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setIdProduct(10);

        when(repository.findById(10)).thenReturn(Optional.of(product));

        Optional<Product> result = repository.findById(10);

        assertTrue(result.isPresent());
        assertEquals(10, result.get().getIdProduct());
    }

    @Test
    void testFindAllByCategory() {
        when(repository.findAllByCategory(ProductCategory.BEER))
                .thenReturn(List.of(new Product()));

        List<Product> list = repository.findAllByCategory(ProductCategory.BEER);

        assertEquals(1, list.size());
    }

    @Test
    void testFindByName() {
        Product p = new Product();
        p.setName("Coca Cola");

        when(repository.findByName("Coca Cola"))
                .thenReturn(Optional.of(p));

        Optional<Product> result = repository.findByName("Coca Cola");

        assertTrue(result.isPresent());
        assertEquals("Coca Cola", result.get().getName());
    }

    @Test
    void testFindLowStock() {
        when(repository.findProductsWithLowStock(5))
                .thenReturn(List.of(new Product()));

        List<Product> list = repository.findProductsWithLowStock(5);

        assertFalse(list.isEmpty());
    }

    @Test
    void testUpdateProduct() {
        Product updated = new Product();
        updated.setName("Actualizado");

        when(repository.updateProduct(updated, 1)).thenReturn(updated);

        Product result = repository.updateProduct(updated, 1);

        assertEquals("Actualizado", result.getName());
    }
}