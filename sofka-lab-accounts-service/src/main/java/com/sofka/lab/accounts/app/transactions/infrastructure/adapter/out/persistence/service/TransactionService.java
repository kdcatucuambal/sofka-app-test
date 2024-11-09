package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.service;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;

import java.util.List;
import java.util.Optional;

public interface TransactionService {


    List<TransactionModel> findAll();

    TransactionModel findById(Long id);

    TransactionModel save(TransactionModel transactionDomain);

    List<TransactionModel> findAllByAccountNumber(String accountNumber);

}
