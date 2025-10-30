package com.colonial.persistence.mapper;

import com.colonial.domain.model.Product;
import com.colonial.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    Product toProduct(ProductEntity entity);
    List<Product> toProducts(List<ProductEntity> entities);
    ProductEntity toEntity(Product product);
    List<ProductEntity> toEntities(List<Product> products);


}