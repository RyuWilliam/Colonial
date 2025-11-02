package com.colonial.web.exception;

import com.colonial.persistence.exceptions.DuplicateProductNameException;
import com.colonial.persistence.exceptions.InsufficientStockException;
import com.colonial.persistence.exceptions.ProductNotFoundException;
import com.colonial.persistence.exceptions.UnknownTransactionTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DuplicateProductNameException.class)
    public ResponseEntity<Error> handleDuplicateNameException(DuplicateProductNameException ex) {
        Error error = new Error("Duplicated product name", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // 409
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Error> handleInsufficientStockException(InsufficientStockException ex) {
        Error error = new Error("Insufficient stock", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Error> handleProductNotFoundException(ProductNotFoundException ex) {
        Error error = new Error("Product not found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404
    }

    @ExceptionHandler(UnknownTransactionTypeException.class)
    public ResponseEntity<Error> handleUnknownTransactionType(UnknownTransactionTypeException ex) {
        Error error = new Error("Unknown transaction type", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleValidationException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        Error error = new Error("format-validation-error", "Ha ocurrido un error en el formato de los datos enviados.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(Exception ex) {
        Error error = new Error("Unexpected error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
