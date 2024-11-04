package com.sofka.lab.accounts.app.controllers.adapters.transactions;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.controllers.adapters.mappers.TransactionControllerMapper;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;
import com.sofka.lab.accounts.app.models.service.movements.MovementService;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Consumer;

@Component
public class TransactionServiceAdapterImpl implements TransactionServiceAdapter {

    private final MovementService movementService;
    private final TransactionControllerMapper transactionMapper;

    public TransactionServiceAdapterImpl(
            MovementService movementService,
            TransactionControllerMapper transactionMapper) {
        this.movementService = movementService;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionGETAllRs execTransactionGETAll() {
        var transactions = new ArrayList<Transaction>();
        Consumer<TransactionEntity> consumer = movement -> transactions.add(transactionMapper.toTransaction(movement));
        this.movementService.findAll().forEach(consumer);
        return TransactionGETAllRs.builder().transactions(transactions).build();
    }

    @Override
    public TransactionPSTRs execTransactionPST(TransactionPSTRq transactionPSTRq) {
        var transactionRq = transactionPSTRq.getTransaction();
        var movementEntity = transactionMapper.toTransactionEntity(transactionRq);
        movementEntity = this.movementService.save(movementEntity);
        var transaction = Transaction.builder().id(movementEntity.getId()).build();
        return TransactionPSTRs.builder().transaction(transaction).build();


    }

    @Override
    public TransactionGETByAccountNumberRs execTransactionGETByAccountNumber(String accountNumber) {
        var transactions = new ArrayList<Transaction>();
        this.movementService.findByAccountNumber(accountNumber)
                .forEach(movement -> transactions.add(transactionMapper.toTransaction(movement)));
        return TransactionGETByAccountNumberRs.builder().transactions(transactions).build();
    }

    @Override
    public TransactionGETByCodeRs execTransactionGETByCode(Long code) {
        var transaction = this.movementService.findById(code);
        return TransactionGETByCodeRs.builder().transaction(transactionMapper.toTransaction(transaction)).build();
    }

    @Override
    public TransactionReportGETByCustomerIdentificationRs
    execTransReportGetByCustomerIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate) {
        var trGETByCustomerIdentification = new TransactionReportGETByCustomerIdentificationRs();
        this.movementService
                .getAccountReportByCustomerIdentification(identification, startDate, endDate)
                .forEach(accountReportDto -> {
                    var transaction = transactionMapper.toTransaction(accountReportDto);
                    trGETByCustomerIdentification.getTransactions().add(transaction);
                });
        trGETByCustomerIdentification.setCustomer(Customer.builder().identification(identification).build());
        return trGETByCustomerIdentification;
    }


}
