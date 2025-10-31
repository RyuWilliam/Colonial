package com.colonial.web.controller;

import com.colonial.domain.enums.TransactionType;
import com.colonial.domain.model.Transaction;
import com.colonial.domain.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAll(){
        return ResponseEntity.ok(transactionService.findAll());
    }



    @PostMapping("/save")
    public ResponseEntity<Transaction> save(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.save(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Integer id) {
        return transactionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/by-date")
    public ResponseEntity<List<Transaction>> getByDateBetween(
            @RequestParam("start") LocalDateTime start,
            @RequestParam("end") LocalDateTime end) {
        return ResponseEntity.ok(transactionService.findByDateBetween(start, end));
    }
    @GetMapping("/search/by-type")
    public ResponseEntity<List<Transaction>> getByType(
            @RequestParam("type") TransactionType type) {
        return ResponseEntity.ok(transactionService.findByType(type));
    }




}
