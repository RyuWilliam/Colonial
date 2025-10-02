package com.colonial.persistence.crud;

import com.colonial.persistence.entity.ItemEntity;
import com.colonial.persistence.entity.ItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, ItemId> {
    List<ItemEntity> findByTransaction_IdTransaction(Integer idTransaction);
    List<ItemEntity> findByProduct_IdProduct(Integer idProduct);
}
