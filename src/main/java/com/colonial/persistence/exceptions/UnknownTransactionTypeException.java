package com.colonial.persistence.exceptions;

import com.colonial.domain.enums.TransactionType;

public class UnknownTransactionTypeException extends RuntimeException {
    public UnknownTransactionTypeException(TransactionType type) {
        super("Transacción desconocida" + type);
    }
}
