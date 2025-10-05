package com.colonial.domain.model;

public class Sale extends Transaction {
    @Override
    public void calculateTotal() {
        double total = getItems().stream()
                .mapToDouble(item -> item.getProduct().getSellPrice()* item.getQuantity())
                .sum();
        setTotal(total);
    }
}
