package com.sofka.lab.accounts.app.transactions.application.business_logic.impl;

import com.sofka.lab.accounts.app.transactions.application.business_logic.BLFindAllTransaction;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.accounts.app.transactions.domain.port.out.repository.TransactionRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BLFindAllTransactionImpl implements BLFindAllTransaction {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionModel> findAll() {
        return transactionRepository.findAll();
    }
}
