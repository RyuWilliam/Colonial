package com.colonial.domain.repository;

import com.colonial.domain.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository {
    Transaction saveTransaction(Transaction transaction);
    Transaction getById(int id);
    List<Transaction> getAll();
    List<Transaction> getByUser(int idUser);
    List<Transaction> getInDate(LocalDateTime timeStart, LocalDateTime timeEnd);
}
