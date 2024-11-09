package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.mapper;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper{

    @Mapping(target = "account.id", source = "accountId")
    @Mapping(target = "account.number", source = "accountNumber")
    TransactionEntity toTransactionEntity(TransactionModel transactionDomain);

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "accountNumber", source = "account.number")
    TransactionModel toTransactionModel(TransactionEntity transactionEntity);

}
