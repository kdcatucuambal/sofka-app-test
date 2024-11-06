package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.mapper;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionDomain;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper{

    TransactionEntity toTransactionEntity(TransactionDomain transactionDomain);
    TransactionDomain toTransactionDomain(TransactionEntity transactionEntity);

}
