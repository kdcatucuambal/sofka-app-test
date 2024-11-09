package com.sofka.lab.accounts.app.transactions.application.business_logic.impl;

import com.sofka.lab.accounts.app.transactions.application.business_logic.BLFindByIdTransaction;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.accounts.app.transactions.domain.port.out.repository.TransactionRepository;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class BLFindByIdTransactionImpl implements BLFindByIdTransaction {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionModel find(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new BusinessLogicException(2003));
    }
}
