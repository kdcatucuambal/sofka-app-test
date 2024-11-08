package com.sofka.lab.accounts.app.transactions.application.service;


import com.sofka.lab.accounts.app.accounts.application.port.out.AccountPersistencePort;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;
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
    private final AccountPersistencePort accountService;
    private final Map<String, TransactionStrategy> trnStrategies;

    @Override
    public List<TransactionDomain> findAll() {
        return transactionService.findAll();
    }

    @Override
    public List<TransactionDomain> findAllByAccountNumber(String accountNumber) {
        return transactionService.findAllByAccountNumber(accountNumber);
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
        var account = getAccount(transactionDomain);
        transactionDomain = strategy.process(transactionDomain, account.getBalance());
        transactionDomain.setAccountId(account.getId());
        transactionDomain.setAccountNumber(account.getNumber());
        account.setBalance(transactionDomain.getBalance());
        accountService.update(account);
        log.info("Transaction saved: {}", transactionDomain);
        return transactionService.save(transactionDomain);
    }

    private AccountDomain getAccount(TransactionDomain transactionDomain) {
        if (transactionDomain.getAccountId() != null) {
            return accountService.findById(transactionDomain.getAccountId()).orElseThrow(() -> new BusinessLogicException(1001));
        }

        if (transactionDomain.getAccountNumber() != null) {
            return accountService.findByNumber(transactionDomain.getAccountNumber()).orElseThrow(() -> new BusinessLogicException(1002));
        }

        throw new BusinessLogicException(1002);
    }

}
