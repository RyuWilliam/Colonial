package com.colonial.persistence.mapper;

import com.colonial.domain.model.Item;
import com.colonial.persistence.entity.ItemEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "idTransaction", ignore = true)
    @Mapping(target = "idProduct", ignore = true)
    ItemEntity toEntity(Item item);
    List<ItemEntity> toEntities(List<Item> items);

    @InheritInverseConfiguration
    Item toItem(ItemEntity item);
    List<Item> toItems(List<ItemEntity> items);
}
