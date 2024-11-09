package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.service.impl;

import com.sofka.lab.accounts.app.transactions.application.business_logic.BLCreateTransaction;
import com.sofka.lab.accounts.app.transactions.application.business_logic.BLFindAllTransaction;
import com.sofka.lab.accounts.app.transactions.application.business_logic.BLFindAllTrnByAccountNumber;
import com.sofka.lab.accounts.app.transactions.application.business_logic.BLFindByIdTransaction;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final BLCreateTransaction blCreateTransaction;
    private final BLFindAllTransaction blFindAllTransaction;
    private final BLFindAllTrnByAccountNumber blFindAllTrnByAccountNumber;
    private final BLFindByIdTransaction blFindByIdTransaction;



    @Override
    public List<TransactionModel> findAll() {
        return blFindAllTransaction.findAll();
    }

    @Override
    public TransactionModel findById(Long id) {
        return blFindByIdTransaction.find(id);
    }

    @Override
    public TransactionModel save(TransactionModel transactionDomain) {
        return blCreateTransaction.create(transactionDomain);
    }

    @Override
    public List<TransactionModel> findAllByAccountNumber(String accountNumber) {
        return blFindAllTrnByAccountNumber.findAll(accountNumber);
    }
}
