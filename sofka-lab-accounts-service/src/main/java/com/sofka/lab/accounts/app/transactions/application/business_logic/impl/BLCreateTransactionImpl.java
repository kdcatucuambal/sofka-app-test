package com.sofka.lab.accounts.app.transactions.application.business_logic.impl;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import com.sofka.lab.accounts.app.transactions.application.business_logic.BLCreateTransaction;
import com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.TransactionStrategy;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.accounts.app.transactions.domain.port.out.repository.TransactionRepository;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;


@AllArgsConstructor
public class BLCreateTransactionImpl implements BLCreateTransaction {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final Map<String, TransactionStrategy> trnStrategies;


    @Override
    public TransactionModel create(TransactionModel transactionModel) {
        validateTransaction(transactionModel);
        var account = getAccount(transactionModel);
        var strategy = trnStrategies.get(transactionModel.getType());
        var transaction = strategy.process(transactionModel, account.getBalance());
        transaction.setAccountId(account.getId());
        transaction.setAccountNumber(account.getNumber());
        account.setBalance(transaction.getBalance());
        accountRepository.save(account);
        return transactionRepository.save(transaction);
    }

    private AccountModel getAccount(TransactionModel transactionModel) {


        if (transactionModel.getAccountId() != null) {
            return accountRepository
                    .findById(transactionModel.getAccountId()).orElseThrow(() -> new BusinessLogicException(1001));
        }

        if (transactionModel.getAccountNumber() != null) {
            return accountRepository
                    .findByAccountNumber(transactionModel.getAccountNumber())
                    .orElseThrow(() -> new BusinessLogicException(1002));
        }

        throw new BusinessLogicException(1002); //TODO: Change to a more specific exception
    }


    private void validateTransaction(TransactionModel transactionModel) {

        if (transactionModel.getAmount() != null
                && transactionModel.getAmount().compareTo(BigDecimal.ZERO) < 0) throw new BusinessLogicException(2002);
    }

}
