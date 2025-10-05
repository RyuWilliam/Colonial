package com.colonial.domain.model;

import java.time.LocalDateTime;

public class Inventory {
    private int idInventory;
    private LocalDateTime lastUpdated;
    private User updatedBy;

    public void updateStock(Product product, int newStock) {
        product.setStock(newStock);
        this.lastUpdated = LocalDateTime.now();
    }
}
