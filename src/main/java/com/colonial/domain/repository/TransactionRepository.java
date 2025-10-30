package com.colonial.domain.repository;

import com.colonial.domain.enums.TransactionType;
import com.colonial.domain.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Integer id);
    List<Transaction> findAll();
    void deleteById(Integer id);
    boolean existsById(Integer id);

    List<Transaction> findByUserId(Integer userId);
    List<Transaction> findByDateBetween(LocalDateTime start, LocalDateTime end);
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByUserIdAndType(Integer userId, TransactionType type);
}