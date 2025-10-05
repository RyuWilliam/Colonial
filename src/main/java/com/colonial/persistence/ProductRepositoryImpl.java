package com.colonial.persistence;


import com.colonial.domain.model.Product;
import com.colonial.domain.repository.ProductRepository;
import com.colonial.persistence.crud.ProductJpaRepository;
import com.colonial.persistence.entity.ProductEntity;
import com.colonial.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


//METERLE EXCEPCIONES A ESTE CUANDO SEA NULL, CUANDO SE REPITA ETC.

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductMapper productMapper;
    private final ProductJpaRepository productRepository;

    public ProductRepositoryImpl(ProductMapper productMapper, ProductJpaRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        return productMapper.toProduct(productRepository.save(entity));
    }

    @Override
    public Product getById(int id) {
        return productMapper.toProduct(productRepository.findById(id).orElse(null));
    }

    @Override
    public List<Product> getAll() {
        return productMapper.toProducts(productRepository.findAll());
    }

    @Override
    public List<Product> getAllByCategory(String Category) {
        return productMapper.toProducts(productRepository.findAllByCategoryIgnoreCase(Category));
    }

    @Override
    public List<Product> getByName(String name) {
        return productMapper.toProducts(productRepository.findAllByCategoryIgnoreCase(name));
    }
}
