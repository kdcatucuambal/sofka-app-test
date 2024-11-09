package com.sofka.lab.accounts.app.transactions.application.business_logic.impl;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.transactions.application.business_logic.BLCreateOpeningTransaction;
import com.sofka.lab.accounts.app.transactions.application.business_logic.constant.ConstantTransaction;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.accounts.app.transactions.domain.port.out.repository.TransactionRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class BLCreateOpeningTransactionImpl implements BLCreateOpeningTransaction {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionModel create(AccountModel accountModel) {
        return transactionRepository.save(buildTransaction(accountModel));
    }

    private TransactionModel buildTransaction(AccountModel accountModel) {
        return TransactionModel.builder()
                .accountId(accountModel.getId())
                .balance(accountModel.getInitBalance())
                .type(ConstantTransaction.TRANSACTION_TYPE_DEPOSIT)
                .date(LocalDateTime.now())
                .amount(accountModel.getInitBalance())
                .build();
    }


}
