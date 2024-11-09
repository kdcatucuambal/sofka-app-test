package com.sofka.lab.accounts.app.transactions.domain.port.out.repository;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    List<TransactionModel> findAll();

    Optional<TransactionModel> findById(Long id);

    TransactionModel save(TransactionModel transactionDomain);

    List<TransactionModel> findAllByAccountNumber(String accountNumber);

}
