package com.colonial.domain.model;

public class Purchase extends Transaction {
    @Override
    public void calculateTotal() {
        double total = getItems().stream()
                .mapToDouble(item -> item.getProduct().getAcquisitionPrice() * item.getQuantity())
                .sum();
        setTotal(total);
    }
}