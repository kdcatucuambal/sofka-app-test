package com.sofka.lab.accounts.app.transactions.application.service;


import com.sofka.lab.accounts.app.transactions.application.port.in.TransactionServicePort;
import com.sofka.lab.accounts.app.transactions.application.port.out.TransactionPersistentPort;
import com.sofka.lab.accounts.app.transactions.application.service.strategy.TransactionStrategy;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionDomain;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class TransactionService implements TransactionServicePort {


    private final TransactionPersistentPort transactionService;
    private final Map<String, TransactionStrategy> trnStrategies;

    @Override
    public List<TransactionDomain> findAll() {
        return transactionService.findAll();
    }

    @Override
    public List<TransactionDomain> findByIdAccountNumber(String accountNumber) {
        return transactionService.findByIdAccountNumber(accountNumber);
    }

    @Override
    public TransactionDomain findById(Long id) {
        return transactionService.findById(id).orElseThrow();
    }

    @Override
    public TransactionDomain save(TransactionDomain transactionDomain) {
        if (transactionDomain.getAmount() != null
                && transactionDomain.getAmount().compareTo(BigDecimal.ZERO) < 0) throw new BusinessLogicException(2002);
        var strategy = trnStrategies.get(transactionDomain.getType());
        //TODO: Validate if account exists
        var account = transactionService.findCustomerByAccountNumber(transactionDomain.getAccountId().toString());
        if (account == null) throw new BusinessLogicException(2001);
        transactionDomain = strategy.process(transactionDomain, account.getBalance());
        //transactionDomain.getBalance(); TODO: Save balance in account
        transactionService.updateAccountBalance(transactionDomain.getAccountId().toString(), transactionDomain.getAmount());
        return transactionService.save(transactionDomain);
    }
}
