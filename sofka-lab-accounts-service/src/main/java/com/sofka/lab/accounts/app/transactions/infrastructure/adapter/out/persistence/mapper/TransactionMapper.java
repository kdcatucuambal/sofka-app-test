package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.mapper;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionDomain;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper{

    @Mapping(target = "account.id", source = "accountId")
    @Mapping(target = "account.number", source = "accountNumber")
    TransactionEntity toTransactionEntity(TransactionDomain transactionDomain);

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "accountNumber", source = "account.number")
    TransactionDomain toTransactionDomain(TransactionEntity transactionEntity);

}
