package com.sofka.lab.accounts.app.accounts.application.service;

import com.sofka.lab.accounts.app.accounts.application.port.in.AccountServicePort;
import com.sofka.lab.accounts.app.accounts.application.port.out.AccountPersistencePort;
import com.sofka.lab.accounts.app.accounts.application.service.factory.AccountFactory;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;
import com.sofka.lab.accounts.app.transactions.application.port.out.TransactionPersistentPort;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionDomain;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class AccountService implements AccountServicePort {

    private final AccountPersistencePort accountPersistencePort;
    private final TransactionPersistentPort transactionServicePort;
//    private final CustomerReadingServicePort customerReadingServicePort;

    private final AccountFactory accountFactory;


    @Override
    public List<AccountDomain> findAll() {
        return accountPersistencePort.findAll();
    }

    @Override
    public AccountDomain findById(Long id) {
        return accountPersistencePort.findById(id).orElseThrow(() -> new BusinessLogicException(1001));
    }

    @Override
    public AccountDomain findByNumber(String number) {
        return accountPersistencePort.findByNumber(number).orElseThrow(() -> new BusinessLogicException(1002));
    }

    @Override
    public AccountDomain save(AccountDomain accountDomain) {
        //TODO: Validate if customer exists


        Long seq = accountPersistencePort.getSeqForAccount();
        accountDomain = accountFactory.getCreatorAccount(accountDomain.getType()).createAccount(seq, accountDomain);
        accountDomain.setStatus(true);
        accountDomain.setBalance(accountDomain.getInitBalance());
        //TODO: Send first transaction
        var accountCreated = accountPersistencePort.save(accountDomain);
        var trnCreated = transactionServicePort.save(getFirstTransaction(accountDomain));
        log.info("Account created: {}", accountCreated);
        log.info("Transaction created: {}", trnCreated);
        return accountCreated;
    }


    @Override
    public AccountDomain update(Long id, AccountDomain accountDomain) {
        var accountFound = accountPersistencePort.findById(id).orElseThrow(() -> new BusinessLogicException(1001));
        accountFound.setId(id);
        accountFound.setType(accountDomain.getType() != null ? accountDomain.getType() : accountFound.getType());
        accountFound.setStatus(accountDomain.getStatus() != null ? accountDomain.getStatus() : accountFound.getStatus());
        return accountPersistencePort.update(accountFound);
    }

    @Override
    public AccountDomain deleteById(Long id) {
        var accountFound = accountPersistencePort.findById(id).orElseThrow(() -> new BusinessLogicException(1001));
        if (!accountFound.getStatus()) throw new BusinessLogicException(1004);
        accountPersistencePort.deleteById(id);
        return accountFound;
    }


    private TransactionDomain getFirstTransaction(AccountDomain accountDomain) {
        return TransactionDomain.builder()
                .accountId(accountDomain.getId())
                .balance(accountDomain.getInitBalance())
                .type("CRE")
                .date(LocalDateTime.now())
                .amount(accountDomain.getInitBalance())
                .build();
    }

}
