package com.colonial.persistence.entity;


import com.colonial.domain.enums.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    public TransactionEntity(){
    }
    @Id
    @Column(name = "id_transaction", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaction;


    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<ItemEntity> items;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}

