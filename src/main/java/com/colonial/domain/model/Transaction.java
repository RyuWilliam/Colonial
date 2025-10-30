package com.colonial.domain.model;

import com.colonial.domain.enums.TransactionType;
import java.time.LocalDateTime;
import java.util.List;

public class Transaction {
    private Integer idTransaction;
    private Integer idUser;
    private TransactionType type;
    private Double total;
    private LocalDateTime date;
    private List<TransactionItem> items;

    public Transaction(Integer idTransaction, Integer idUser, TransactionType type, LocalDateTime date, List<TransactionItem> items) {
        this.idTransaction = idTransaction;
        this.idUser = idUser;
        this.type = type;
        this.date = date;
        this.items = items;
    }

    public Transaction() {

    }


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<TransactionItem> getItems() {
        return items;
    }

    public void setItems(List<TransactionItem> items) {
        this.items = items;
    }
}