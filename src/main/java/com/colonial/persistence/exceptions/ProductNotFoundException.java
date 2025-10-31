package com.colonial.persistence.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer productId) {
        super("No se ha encontrado el producto con id " + productId);
    }
}
