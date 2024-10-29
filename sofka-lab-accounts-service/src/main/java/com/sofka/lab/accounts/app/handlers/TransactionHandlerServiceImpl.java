package com.sofka.lab.accounts.app.handlers;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.models.entity.AccountEntity;
import com.sofka.lab.accounts.app.models.entity.MovementEntity;
import com.sofka.lab.accounts.app.models.service.MovementService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Component
public class TransactionHandlerServiceImpl implements TransactionHandlerService {

    private final MovementService movementService;

    public TransactionHandlerServiceImpl(MovementService movementService) {
        this.movementService = movementService;
    }

    @Override
    public TransactionGETAllRs execTransactionGETAll() {
        var transactions = new ArrayList<Transaction>();
        this.movementService.findAll().forEach(movement -> {
            transactions.add(mapTransaction(movement));
        });
        return new TransactionGETAllRs(transactions);
    }

    @Override
    public TransactionPSTRs execTransactionPST(TransactionPSTRq transactionPSTRq) {
        var transactionRq = transactionPSTRq.getTransaction();
        var movementEntity = new MovementEntity();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setNumber(transactionRq.getAccount().getNumber());
        movementEntity.setAccount(accountEntity);
        movementEntity.setAmount(transactionRq.getAmount());
        movementEntity.setType(transactionRq.getType());
        movementEntity = this.movementService.save(movementEntity);
        return new TransactionPSTRs(Transaction.builder().id(movementEntity.getId().intValue()).build());
    }

    @Override
    public TransactionGETByAccountNumberRs execTransactionGETByAccountNumber(String accountNumber) {
        var transactions = new ArrayList<Transaction>();
        this.movementService.findByAccountNumber(accountNumber).forEach(movement -> {
            var transaction = Transaction.builder()
                    .id(movement.getId().intValue())
                    .balance(movement.getBalance())
                    .amount(movement.getAmount())
                    .date(movement.getDate())
                    .type(movement.getType())
                    .build();
            transactions.add(transaction);
        });
        return new TransactionGETByAccountNumberRs(transactions);
    }

    @Override
    public TransactionGETByCodeRs execTransactionGETByCode(Long code) {
        var transaction = this.movementService.findById(code);
        return new TransactionGETByCodeRs(mapTransaction(transaction));
    }

    @Override
    public TransactionReportGETByCustomerIdentificationRs
    execTransReportGetByCustomerIdentification(String identification, LocalDateTime startDate, LocalDateTime endDate) {
        var trGETByCustomerIdentification = new TransactionReportGETByCustomerIdentificationRs();
        this.movementService
                .getAccountReportByCustomerIdentification(identification, startDate, endDate)
                .forEach(accountReportDto -> {
                    var transaction = Transaction.builder()
                            .type(accountReportDto.getType())
                            .balance(accountReportDto.getAvailableBalance())
                            .date(accountReportDto.getDate()) //TODO: Pendiente
                            .account(Account.builder().number(accountReportDto.getAccountNumber()).build())
                            .amount(accountReportDto.getMovement())
                            .build();
                    trGETByCustomerIdentification.getTransactions().add(transaction);
                });


        trGETByCustomerIdentification.setCustomer(Customer.builder().identification(identification).build());

        return trGETByCustomerIdentification;
    }

    private Transaction mapTransaction(MovementEntity movement) {
        var account = Account.builder().id(movement.getAccount().getId()).build();
        return Transaction.builder()
                .account(account)
                .id(movement.getId().intValue())
                .balance(movement.getBalance())
                .amount(movement.getAmount())
                .date(null) //TODO Check date converstions
                .type(movement.getType())
                .build();
    }

}
