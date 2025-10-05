package com.colonial.persistence.mapper;


import com.colonial.domain.model.Transaction;
import com.colonial.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {


    @Mapping(target = "type", ignore = true)
    TransactionEntity toEntity(Transaction transaction);
    List<TransactionEntity> toEntities(List<Transaction> transactions);

    Transaction toTransaction(TransactionEntity transaction);
    List<Transaction> toTransactions(List<TransactionEntity> transactions);

}
