package com.colonial.persistence.mapper;


import com.colonial.domain.model.Transaction;
import com.colonial.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {
    private final ItemMapper itemMapper;


    public TransactionMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }


    public TransactionEntity toEntity(Transaction transaction){
    TransactionEntity entity = new TransactionEntity();
    entity.setIdUser(transaction.getIdUser());
    entity.setDate(LocalDateTime.now());
    entity.setTotal(transaction.getTotal());
    entity.setItems(itemMapper.toEntities(transaction.getItems()));
    entity.setType(transaction.getType());
    return entity;
    }

    public Transaction toTransaction(TransactionEntity entity){
        if (entity == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setIdTransaction(entity.getIdTransaction());
        transaction.setIdUser(entity.getIdUser());
        transaction.setDate(entity.getDate());
        transaction.setTotal(entity.getTotal());
        transaction.setType(entity.getType());
        transaction.setItems(itemMapper.toItems(entity.getItems()));
        return transaction;
    }
    public List<TransactionEntity> toEntities(List<Transaction> transactions) {
        if (transactions == null) {
            return null;
        }
        return transactions.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
    public List<Transaction> toTransactions(List<TransactionEntity> entities){
        if(entities == null){
            return null;
        }
        return entities.stream()
                .map(this::toTransaction)
                .collect(Collectors.toList());
    }





}
