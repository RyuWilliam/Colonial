
package com.colonial.persistence.crud;

import com.colonial.domain.enums.TransactionType;
import com.colonial.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByUser_IdUser(Integer idUser);

    List<TransactionEntity> findByDateBetween(LocalDateTime start, LocalDateTime end);

    List<TransactionEntity> findByType(TransactionType type);

    List<TransactionEntity> findByUser_IdUserAndType(Integer idUser, TransactionType type);


}