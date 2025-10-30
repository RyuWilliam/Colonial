package com.colonial.domain.model;

public class TransactionItem {

private Integer idItem;
private Integer idTransaction;
private Integer idProduct;
private int quantity;
private Double price;
private Product product;

    public TransactionItem(Integer idItem, Integer idTransaction, Integer idProduct, int quantity, Double price, Product product) {
        this.idItem = idItem;
        this.idTransaction = idTransaction;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public TransactionItem(){

    }
    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}