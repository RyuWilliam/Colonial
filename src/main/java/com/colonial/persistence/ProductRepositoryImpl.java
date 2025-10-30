package com.colonial.persistence;

import com.colonial.domain.enums.ProductCategory;
import com.colonial.domain.model.Product;
import com.colonial.domain.repository.ProductRepository;

import com.colonial.persistence.crud.ProductJpaRepository;
import com.colonial.persistence.entity.ProductEntity;
import com.colonial.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryImpl(ProductJpaRepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        return productMapper.toProduct(productJpaRepository.save(entity));
    }

    @Override
    public Optional<Product> findById(Integer id) {
        Optional<ProductEntity> product = productJpaRepository.findById(id);
        return product.map(productMapper::toProduct);

    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = productJpaRepository.findAll();
        return productMapper.toProducts(entities);
    }

    @Override
    public void deleteById(Integer id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByCategory(ProductCategory category) {
        List<ProductEntity> entities = productJpaRepository.findAllByCategory(category);
        return productMapper.toProducts(entities);
     }

    @Override
    public List<Product> findAllByName(String name) {
        List<ProductEntity> entities = productJpaRepository.findAllByNameContainingIgnoreCase(name);
        return productMapper.toProducts(entities);
    }

    @Override
    public Optional<Product> findByName(String name) {
        Optional<ProductEntity> entity = productJpaRepository.findByNameIgnoreCase(name);
        return entity.map(productMapper::toProduct);
    }

    @Override
    public List<Product> findProductsWithLowStock(Integer threshold) {
        List<ProductEntity> entities = productJpaRepository.findProductsWithLowStock(threshold);
        return productMapper.toProducts(entities);
    }

    @Override
    public List<Product> findAvailableProducts() {
        List<ProductEntity> entities = productJpaRepository.findAvailableProducts();
        return productMapper.toProducts(entities);
    }

    @Override
    public List<Product> findOutOfStockProducts() {
        List<ProductEntity> entities = productJpaRepository.findOutOfStockProducts();
        return productMapper.toProducts(entities);
    }
}