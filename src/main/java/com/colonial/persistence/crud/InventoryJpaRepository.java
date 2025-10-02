package com.colonial.persistence.crud;

import com.colonial.persistence.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, Integer> {
}
