package com.sofka.lab.accounts.app.controllers.adapters.mappers;

import com.sofka.bank.objects.Account;
import com.sofka.bank.objects.Transaction;
import com.sofka.lab.accounts.app.models.dtos.AccountReportDto;
import com.sofka.lab.accounts.app.models.dtos.TransactionDto;
import com.sofka.lab.accounts.app.models.entities.AccountEntity;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;
import com.sofka.lab.accounts.app.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionControllerMapper {


    @Mapping(target = "type", expression = "java(fromTypeEnum(transaction.getType()))")
    TransactionDto toTransactionDto(Transaction transaction);

    @Mapping(target = "type", expression = "java(toTypeEnum(transactionDto.getType()))")
    @Mapping(target = "description", expression = "java(getTransactionDescription(transactionDto))")
    Transaction toTransaction(TransactionDto transactionDto);



    @Mapping(target = "type", expression = "java(fromTypeEnum(transaction.getType()))")
    @Mapping(target = "account", expression = "java(toAccountEntityFromTransaction(transaction))")
    TransactionEntity toTransactionEntity(Transaction transaction);

    @Mapping(target = "type", expression = "java(toTypeEnum(transactionEntity.getType()))")
    @Mapping(target = "description", expression = "java(getTransactionDescription(transactionEntity))")
    @Mapping(target = "account", expression = "java(toAccount(transactionEntity))")
    Transaction toTransaction(TransactionEntity transactionEntity);

    @Mapping(target = "type", expression = "java(toTypeEnum(accountReportDto.getType()))")
    @Mapping(target = "description", expression = "java(getTransactionDescription(accountReportDto))")
    @Mapping(target = "account", expression = "java(toAccount(accountReportDto))")
    @Mapping(target = "amount", source = "movement")
    @Mapping(target = "balance", source = "availableBalance")
    Transaction toTransaction(AccountReportDto accountReportDto);


    default AccountEntity toAccountEntityFromTransaction(Transaction transaction) {
        return AccountEntity.builder()
                .id(transaction.getAccount().getId() != null ? transaction.getAccount().getId() : null)
                .number(transaction.getAccount().getNumber() != null ? transaction.getAccount().getNumber() : null)
                .build();
    }

    default String fromTypeEnum(Transaction.TypeEnum typeEnum) {
        return typeEnum.getValue();
    }

    default Transaction.TypeEnum toTypeEnum(String type) {
        return Transaction.TypeEnum.fromValue(type);
    }

    default Account.TypeEnum toTypeAccountEnum(String type) {
        return Account.TypeEnum.fromValue(type);
    }

    default String getTransactionDescription(TransactionDto transactionDto) {
        var type = Transaction.TypeEnum.fromValue(transactionDto.getType());
        var amount = transactionDto.getAmount();
        return type.equals(Transaction.TypeEnum.CRE)
                ? Constants.CREDIT_TEXT.concat(amount.toPlainString())
                : Constants.DEBIT_TEXT.concat(amount.toPlainString());
    }

    default String getTransactionDescription(TransactionEntity transaction) {
        var type = Transaction.TypeEnum.fromValue(transaction.getType());
        var amount = transaction.getAmount();
        return type.equals(Transaction.TypeEnum.CRE)
                ? Constants.CREDIT_TEXT.concat(amount.toPlainString())
                : Constants.DEBIT_TEXT.concat(amount.toPlainString());
    }

    default String getTransactionDescription(AccountReportDto accountReportDto) {
        var type = Transaction.TypeEnum.fromValue(accountReportDto.getType());
        var amount = accountReportDto.getMovement();
        return type.equals(Transaction.TypeEnum.CRE)
                ? Constants.CREDIT_TEXT.concat(amount.toPlainString())
                : Constants.DEBIT_TEXT.concat(amount.toPlainString());
    }

    default Account toAccount(TransactionEntity transactionEntity) {
        return Account.builder()
                .id(transactionEntity.getAccount().getId() != null ? transactionEntity.getAccount().getId() : null)
                .number(transactionEntity.getAccount().getNumber() != null ? transactionEntity.getAccount().getNumber() : null)
                .build();
    }

    default Account toAccount(AccountReportDto accountReportDto) {
        return Account.builder()
                .number(accountReportDto.getAccountNumber())
                .build();
    }

}
