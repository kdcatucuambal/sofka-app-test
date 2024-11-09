package com.sofka.lab.accounts.app.transactions.application.business_logic.impl;

import com.sofka.lab.accounts.app.transactions.application.business_logic.BLFindAllTrnByAccountNumber;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.accounts.app.transactions.domain.port.out.repository.TransactionRepository;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BLFindAllTrnByAccountNumberImpl implements BLFindAllTrnByAccountNumber {
    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionModel> findAll(String accountNumber) {
        var transactions = transactionRepository.findAllByAccountNumber(accountNumber);
        if (transactions.isEmpty()) {
            throw new BusinessLogicException(2006);
        }
        return transactions;
    }


}
