package com.colonial.web.controller;

import com.colonial.domain.enums.TransactionType;
import com.colonial.domain.model.Transaction;
import com.colonial.domain.model.TransactionItem;
import com.colonial.domain.service.TransactionService;
import com.colonial.persistence.crud.TransactionJpaRepository;
import com.colonial.persistence.entity.TransactionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionJpaRepository transactionJpaRepository;
    private final TransactionService transactionService;

    public TransactionController(TransactionJpaRepository transactionJpaRepository, TransactionService transactionService) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.transactionService = transactionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAll(){
        return ResponseEntity.ok(transactionService.findAll());
    }

    @PostMapping("/saveRaw")
    public ResponseEntity<TransactionEntity> saveRaw(@RequestBody TransactionEntity entity){
        return ResponseEntity.ok(transactionJpaRepository.save(entity));
    }

    @PostMapping("/save")
    public ResponseEntity<Transaction> save(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.save(transaction));
    }

    @GetMapping("/allRaw")
    public ResponseEntity<List<TransactionEntity>> getAllRaw(){
        return ResponseEntity.ok(transactionJpaRepository.findAll());
    }
}
