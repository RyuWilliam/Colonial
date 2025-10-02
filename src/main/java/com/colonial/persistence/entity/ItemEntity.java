package com.colonial.persistence.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "item")
@IdClass(ItemId.class)
public class ItemEntity {
    @Id
    @Column(name = "id_transaction")
    private Integer idTransaction;

    @Id
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_transaction", referencedColumnName = "id_transaction", insertable = false, updatable = false)
    private TransactionEntity transaction;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", insertable = false, updatable = false)
    private ProductEntity product;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }
}

