package com.colonial.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

public class ItemId implements Serializable {
    private Integer idTransaction;
    private Integer idProduct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemId that)) return false;
        return Objects.equals(idTransaction, that.idTransaction) &&
                Objects.equals(idProduct, that.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaction, idProduct);
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
}

