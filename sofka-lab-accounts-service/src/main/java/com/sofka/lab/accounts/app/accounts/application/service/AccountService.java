package com.sofka.lab.accounts.app.accounts.application.service;

import com.sofka.lab.accounts.app.accounts.application.port.in.AccountServicePort;
import com.sofka.lab.accounts.app.accounts.application.port.out.AccountPersistencePort;
import com.sofka.lab.accounts.app.accounts.application.service.factory.AccountFactory;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class AccountService implements AccountServicePort {

    private final AccountPersistencePort accountPersistencePort;
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
        Long seq = 1L; //TODO: Implement sequence
        //TODO: Validate if customer exists
        accountDomain = accountFactory.getCreatorAccount(accountDomain.getType())
                .createAccount(seq, accountDomain);
        //TODO: Send first transaction
        return accountPersistencePort.save(accountDomain);
    }

    @Override
    public AccountDomain update(Long id, AccountDomain accountDomain) {
        var accountFound = accountPersistencePort.findById(id).orElseThrow(() -> new BusinessLogicException(1001));
        accountFound.setId(id);
        accountFound.setType(accountDomain.getType() != null ? accountDomain.getType() : accountFound.getType());
        accountFound.setStatus(accountDomain.getStatus() != null ? accountDomain.getStatus() : accountFound.getStatus());
        return accountPersistencePort.update(accountDomain);
    }

    @Override
    public AccountDomain deleteById(Long id) {
        var accountFound = accountPersistencePort.findById(id).orElseThrow(() -> new BusinessLogicException(1001));
        if (!accountFound.getStatus()) throw new BusinessLogicException(1004);
        accountPersistencePort.deleteById(id);
        return accountFound;
    }
}
