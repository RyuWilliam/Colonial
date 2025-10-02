package com.colonial.persistence.crud;

import com.colonial.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findByUser_IdUser(Integer idUser);
    List<TransactionEntity> findByDateBetween(LocalDateTime start, LocalDateTime end);
}

