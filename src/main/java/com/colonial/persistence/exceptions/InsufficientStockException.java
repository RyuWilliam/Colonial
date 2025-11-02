package com.colonial.persistence.exceptions;

import com.colonial.domain.model.TransactionItem;
import com.colonial.persistence.entity.ProductEntity;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(ProductEntity product, TransactionItem item) {
        super("Cantidad de producto insuficiente: " +  product.getName() +"\nCantidad disponible:" + product.getStock() + "\nCantidad solicitada: " + item.getQuantity());
    }
}
