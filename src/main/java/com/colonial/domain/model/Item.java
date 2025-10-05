package com.colonial.domain.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Item {

    @Positive(message = "La cantidad debe ser mayor que 0")
    private int quantity;

    @NotNull(message = "Debe asociarse a una transacción")
    private Transaction transaction;

    @NotNull(message = "Debe asociarse a un producto")
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
