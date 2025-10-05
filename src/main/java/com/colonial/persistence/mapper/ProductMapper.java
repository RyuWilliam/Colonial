package com.colonial.persistence.mapper;

import com.colonial.domain.model.Product;
import com.colonial.persistence.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    ProductEntity toEntity(Product product);
    List<ProductEntity> toEntities(List<Product> products);

    @InheritInverseConfiguration
    Product toProduct(ProductEntity product);
    List<Product> toProducts(List<ProductEntity> products);

}
