package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.in.rest.mapper;

import com.sofka.bank.objects.Transaction;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionRestMapper {

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "accountNumber", source = "account.number")
    TransactionDomain toTransactionDomain(Transaction transaction);

    @Mapping(target = "account.id", source = "accountId")
    @Mapping(target = "account.number", source = "accountNumber")
    @Mapping(target = "description", expression = "java(getDescription(transactionDomain))")
    Transaction toTransaction(TransactionDomain transactionDomain);

    default String getDescription(TransactionDomain transactionDomain) {
        if (transactionDomain.getType().equals("CRE")) {
            return "Depósito de $: " + transactionDomain.getAmount();
        } else if (transactionDomain.getType().equals("DBT")) {
            return "Retiro de $: " + transactionDomain.getAmount();
        }
        throw new IllegalArgumentException("Invalid transaction type");
    }

}
