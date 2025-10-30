package com.colonial.domain.service;

import com.colonial.domain.enums.TransactionType;
import com.colonial.domain.model.Product;
import com.colonial.domain.model.Transaction;
import com.colonial.domain.model.TransactionItem;
import com.colonial.domain.model.User;
import com.colonial.domain.repository.ProductRepository;
import com.colonial.domain.repository.TransactionRepository;
import com.colonial.domain.repository.UserRepository;
import com.colonial.persistence.entity.ItemEntity;
import com.colonial.persistence.entity.ProductEntity;
import com.colonial.persistence.entity.TransactionEntity;
import com.colonial.persistence.entity.UserEntity;
import com.colonial.persistence.crud.TransactionJpaRepository;
import com.colonial.persistence.mapper.ProductMapper;
import com.colonial.persistence.mapper.TransactionMapper;
import com.colonial.persistence.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;


    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction){
        return transactionRepository.save(transaction);

    }
    public Optional<Transaction> findById(Integer id){
        return transactionRepository.findById(id);
    }
    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }
    public void deleteById(Integer id){
        transactionRepository.deleteById(id);
    }
    public boolean existsById(Integer id){
        return transactionRepository.existsById(id);
    }

    public List<Transaction> findByUserId(Integer userId){
        return transactionRepository.findByUserId(userId);
    }
    public List<Transaction> findByDateBetween(LocalDateTime start, LocalDateTime end){
        return transactionRepository.findByDateBetween(start,end);
    }
    public List<Transaction> findByType(TransactionType type){
        return transactionRepository.findByType(type);
    }
    public List<Transaction> findByUserIdAndType(Integer userId, TransactionType type){
        return transactionRepository.findByUserIdAndType(userId, type);
    }
}