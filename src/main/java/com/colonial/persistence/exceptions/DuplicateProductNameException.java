package com.colonial.persistence.exceptions;

public class DuplicateProductNameException extends RuntimeException {
    public DuplicateProductNameException(String productName) {
        super("Ya existe un producto con el nombre: " + productName);
    }
}