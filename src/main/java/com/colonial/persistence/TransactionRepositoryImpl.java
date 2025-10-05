package com.colonial.persistence;

import com.colonial.domain.model.Purchase;
import com.colonial.domain.model.Sale;
import com.colonial.domain.model.Transaction;
import com.colonial.domain.repository.TransactionRepository;
import com.colonial.persistence.crud.TransactionJpaRepository;
import com.colonial.persistence.entity.TransactionEntity;
import com.colonial.persistence.mapper.TransactionMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.colonial.domain.enums.TransactionType.PURCHASE;
import static com.colonial.domain.enums.TransactionType.SALE;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{
    private final TransactionMapper transactionMapper;
    private final TransactionJpaRepository transactionRepository;

    public TransactionRepositoryImpl(TransactionMapper transactionMapper, TransactionJpaRepository transactionRepository) {
        this.transactionMapper = transactionMapper;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        TransactionEntity entity = transactionMapper.toEntity(transaction);
        if(transaction instanceof Purchase){
            entity.setType(PURCHASE);
        }
        if(transaction instanceof Sale){
            entity.setType(SALE);
        }
        return transactionMapper.toTransaction(transactionRepository.save(entity));
    }

    @Override
    public Transaction getById(int id) {
        return transactionMapper.toTransaction(transactionRepository.findById(id).orElse(null));
    }

    @Override
    public List<Transaction> getAll() {
        return transactionMapper.toTransactions(transactionRepository.findAll());
    }

    @Override
    public List<Transaction> getByUser(int idUser) {
        return transactionMapper.toTransactions(transactionRepository.findByUser_IdUser(idUser));
    }

    @Override
    public List<Transaction> getInDate(LocalDateTime dateStart, LocalDateTime dateEnd) {
        return transactionMapper.toTransactions(transactionRepository.findByDateBetween(dateStart,dateEnd));
    }
}
