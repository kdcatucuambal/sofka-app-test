package com.sofka.lab.accounts.app.controllers.adapters.transactions;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.controllers.adapters.mappers.TransactionControllerMapper;
import com.sofka.lab.accounts.app.models.entities.AccountEntity;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;
import com.sofka.lab.accounts.app.models.service.movements.MovementService;
import com.sofka.lab.accounts.app.utils.Constants;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
        Consumer<TransactionEntity> consumer = movement -> {
            transactions.add(transactionMapper.toTransaction(movement));
        };
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
//        var accountEntity = AccountEntity.builder().number(transactionRq.getAccount().getNumber()).build();
//        movementEntity.setAccount(accountEntity);
//        movementEntity.setAmount(transactionRq.getAmount());
//        movementEntity.setType(String.valueOf(transactionRq.getType()));


    }

    @Override
    public TransactionGETByAccountNumberRs execTransactionGETByAccountNumber(String accountNumber) {
        var transactions = new ArrayList<Transaction>();
        this.movementService.findByAccountNumber(accountNumber).forEach(movement -> {
//            var transaction = Transaction.builder()
//                    .id(movement.getId().intValue())
//                    .balance(movement.getBalance())
//                    .amount(movement.getAmount())
//                    .date(movement.getDate())
//                    .type(Transaction.TypeEnum.valueOf(movement.getType()))
//                    .description(getTransactionDescription(Transaction.TypeEnum
//                            .valueOf(movement.getType()), movement.getAmount()))
//                    .build();
            transactions.add(transactionMapper.toTransaction(movement));
        });
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
//                    var transaction = Transaction.builder()
//                            .type(Transaction.TypeEnum.valueOf(accountReportDto.getType()))
//                            .balance(accountReportDto.getAvailableBalance())
//                            .date(accountReportDto.getDate())
//                            .account(Account.builder().number(accountReportDto.getAccountNumber()).build())
//                            .description(getTransactionDescription(Transaction.TypeEnum
//                                    .valueOf(accountReportDto.getType()), accountReportDto.getMovement()))
//                            .amount(accountReportDto.getMovement())
//                            .build();
                    var transaction = transactionMapper.toTransaction(accountReportDto);
                    trGETByCustomerIdentification.getTransactions().add(transaction);
                });


        trGETByCustomerIdentification.setCustomer(Customer.builder().identification(identification).build());

        return trGETByCustomerIdentification;
    }
//
//    private Transaction mapTransaction(TransactionEntity movement) {
//        var account = Account.builder().id(movement.getAccount().getId()).build();
//        var trnType = Transaction.TypeEnum.valueOf(movement.getType());
//        return Transaction.builder()
//                .account(account)
//                .id(movement.getId().intValue())
//                .balance(movement.getBalance())
//                .amount(movement.getAmount())
//                .date(movement.getDate())
//                .description(getTransactionDescription(trnType, movement.getAmount()))
//                .type(trnType)
//                .build();
//    }

    private String getTransactionDescription(Transaction.TypeEnum type, BigDecimal amount) {
        return type.equals(Transaction.TypeEnum.CRE)
                ? Constants.CREDIT_TEXT.concat(amount.toPlainString())
                : Constants.DEBIT_TEXT.concat(amount.toPlainString());
    }


}
