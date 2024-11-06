package com.sofka.lab.accounts.app.transactions.application.port.in;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionDomain;

import java.util.List;

public interface TransactionServicePort {

    List<TransactionDomain> findAll();

    List<TransactionDomain> findByIdAccountNumber(String accountNumber);

    TransactionDomain findById(Long id);

    TransactionDomain save(TransactionDomain transactionDomain);

}
