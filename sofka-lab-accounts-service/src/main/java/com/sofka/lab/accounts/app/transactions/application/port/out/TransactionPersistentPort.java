package com.sofka.lab.accounts.app.transactions.application.port.out;

import com.sofka.bank.objects.Account;
import com.sofka.bank.objects.Customer;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionDomain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionPersistentPort {

    List<TransactionDomain> findAll();

    Optional<TransactionDomain> findById(Long id);

    TransactionDomain save(TransactionDomain transactionDomain);

    List<TransactionDomain> findByIdAccountNumber(String accountNumber);

    void updateAccountBalance(String accountNumber, BigDecimal amount);

    Account findCustomerByAccountNumber(String accountNumber);

}
