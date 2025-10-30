package com.colonial.persistence.mapper;


import com.colonial.domain.model.TransactionItem;
import com.colonial.persistence.entity.ItemEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {
    private final ProductMapper productMapper;

    public ItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public TransactionItem toItem(ItemEntity entity){
    if(entity == null){
        return null;
    }
    TransactionItem item = new TransactionItem();
    item.setIdItem(entity.getIdItem());
    item.setQuantity(entity.getQuantity());
    item.setProduct(productMapper.toProduct(entity.getProduct()));
    item.setIdTransaction(entity.getIdTransaction());
    item.setIdProduct(entity.getIdProduct());
    item.setPrice(entity.getPrice());
    return item;
    }

    public ItemEntity toEntity(TransactionItem item){
        if(item == null){
            return null;
        }
        ItemEntity entity = new ItemEntity();
        entity.setPrice(item.getPrice()!= null ? item.getPrice() : 0.0);
        entity.setIdProduct(item.getIdProduct());
        entity.setQuantity(item.getQuantity());
        entity.setIdTransaction(item.getIdTransaction());
        return entity;
    }

    public List<ItemEntity> toEntities(List<TransactionItem> items){
        if (items == null) {
            return null;
        }
        List<ItemEntity> entities = new ArrayList<>();
        for (TransactionItem item : items) {
            entities.add(toEntity(item));
        }
        return entities;
    }

    public List<TransactionItem> toItems(List<ItemEntity> entities){
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toItem)
                .collect(Collectors.toList());
    }
}
