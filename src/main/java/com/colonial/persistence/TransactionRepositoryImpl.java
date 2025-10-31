package com.colonial.persistence;

import com.colonial.domain.enums.TransactionType;
import com.colonial.domain.model.Transaction;
import com.colonial.domain.repository.TransactionRepository;
import com.colonial.persistence.crud.ProductJpaRepository;
import com.colonial.persistence.crud.TransactionJpaRepository;
import com.colonial.persistence.entity.ItemEntity;
import com.colonial.persistence.entity.ProductEntity;
import com.colonial.persistence.entity.TransactionEntity;
import com.colonial.persistence.exceptions.ProductNotFoundException;
import com.colonial.persistence.exceptions.UnknownTransactionTypeException;
import com.colonial.persistence.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionJpaRepository transactionJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final TransactionMapper transactionMapper;


    public TransactionRepositoryImpl(TransactionJpaRepository transactionJpaRepository, ProductJpaRepository productJpaRepository, TransactionMapper transactionMapper) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        double total = 0.0;
        if(transaction.getType() == TransactionType.PURCHASE){
            total = calculateTotalForPurchase(transaction);
        }
        else if(transaction.getType() == TransactionType.SALE){
            total = calculateTotalForSale(transaction);
        }
        else{
            throw new UnknownTransactionTypeException(transaction.getType());
        }
            transaction.setTotal(total);
            TransactionEntity entity = transactionMapper.toEntity(transaction);
            TransactionEntity saved = transactionJpaRepository.save(entity);
            if(saved.getItems()!= null ){
            assignItems(saved);
            }
            saved = transactionJpaRepository.save(saved);
            return transactionMapper.toTransaction(saved);
    }


    public void assignItems(TransactionEntity entity){
            for(ItemEntity item: entity.getItems()){
                item.setIdTransaction(entity.getIdTransaction());
                item.setTransaction(entity);
            }
    }



    Double calculateTotalForSale(Transaction transaction){
        double total = 0.0;
        for(var item: transaction.getItems()){
            ProductEntity product = productJpaRepository.findById(item.getIdProduct())
                    .orElseThrow(() -> new ProductNotFoundException(item.getIdProduct()));
            double itemPrice = product.getAcquisitionPrice() * item.getQuantity() * 1.2;
            item.setPrice(itemPrice);
            product.setStock(product.getStock() - item.getQuantity());
            total += itemPrice;
        }
        return total;
    }
    Double calculateTotalForPurchase(Transaction transaction){
        double total = 0.0;
        for(var item: transaction.getItems()){
            ProductEntity product = productJpaRepository.findById(item.getIdProduct())
                    .orElseThrow(() -> new ProductNotFoundException(item.getIdProduct()));
            double itemPrice = product.getAcquisitionPrice() * item.getQuantity();
            item.setPrice(itemPrice);
            product.setStock(product.getStock() + item.getQuantity());
            total += itemPrice;
        }
        return total;
    }

    @Override
    public Optional<Transaction> findById(Integer id) {
        Optional<TransactionEntity> entity = transactionJpaRepository.findById(id);
        return entity.map(transactionMapper::toTransaction);
    }

    @Override
    public List<Transaction> findAll() {
        List<TransactionEntity> entities = transactionJpaRepository.findAll();
        return transactionMapper.toTransactions(entities);
    }

    @Override
    public void deleteById(Integer id) {
        transactionJpaRepository.deleteById(id);

    }

    @Override
    public boolean existsById(Integer id) {
        return transactionJpaRepository.existsById(id);
    }

    @Override
    public List<Transaction> findByUserId(Integer userId) {
        List<TransactionEntity> entities = transactionJpaRepository.findByUser_IdUser(userId);
        return transactionMapper.toTransactions(entities);
    }

    @Override
    public List<Transaction> findByDateBetween(LocalDateTime start, LocalDateTime end) {
        List<TransactionEntity> entities = transactionJpaRepository.findByDateBetween(start,end);
        return transactionMapper.toTransactions(entities);
    }

    @Override
    public List<Transaction> findByType(TransactionType type) {
        List<TransactionEntity> entities = transactionJpaRepository.findByType(type);
        return transactionMapper.toTransactions(entities);
    }

    @Override
    public List<Transaction> findByUserIdAndType(Integer userId, TransactionType type) {
        List<TransactionEntity> entities = transactionJpaRepository.findByUser_IdUserAndType(userId, type);
        return transactionMapper.toTransactions(entities);
    }




}