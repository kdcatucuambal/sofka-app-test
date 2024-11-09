package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.in.rest.adapter;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.in.rest.mapper.TransactionRestMapper;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class TransactionRestAdapterImpl implements TransactionRestAdapter {

    private final TransactionService trnService;
    private final TransactionRestMapper trnMapper;

    @Override
    public TransactionGETAllRs execTransactionGETAll() {
        var transactions = trnService.findAll().stream().map(trnMapper::toTransaction).toList();
        return TransactionGETAllRs.builder().transactions(transactions).build();
    }

    @Override
    public TransactionPSTRs execTransactionPST(TransactionPSTRq transactionPSTRs) {
        var transaction = trnMapper.toTransactionDomain(transactionPSTRs.getTransaction());
        transaction = trnService.save(transaction);
        return TransactionPSTRs.builder().transaction(Transaction.builder().id(transaction.getId()).build()).build();
    }

    @Override
    public TransactionGETByAccountNumberRs execTransactionGETByAccountNumber(String accountNumber) {
        var transactions = trnService.findAllByAccountNumber(accountNumber).stream().map(trnMapper::toTransaction).toList();
        return TransactionGETByAccountNumberRs.builder().transactions(transactions).build();
    }

    @Override
    public TransactionGETByCodeRs execTransactionGETByCode(Long code) {
        var transaction = trnService.findById(code);
        return TransactionGETByCodeRs.builder().transaction(trnMapper.toTransaction(transaction)).build();
    }

    @Override
    public TransactionReportGETByCustomerIdentificationRs execTransReportGetByCustomerIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate) {
        //TODO: Implementar
        return null;
    }
}
