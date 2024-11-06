package com.sofka.lab.accounts.app.accounts.application.service;

import com.sofka.lab.accounts.app.accounts.application.port.in.AccountServicePort;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;

import java.util.List;

public class AccountService implements AccountServicePort {
    @Override
    public List<AccountDomain> findAll() {
        return null;
    }

    @Override
    public AccountDomain findById(Long id) {
        return null;
    }

    @Override
    public AccountDomain findByNumber(Long id) {
        return null;
    }

    @Override
    public AccountDomain save(AccountDomain accountDomain) {
        return null;
    }

    @Override
    public AccountDomain update(AccountDomain accountDomain) {
        return null;
    }

    @Override
    public AccountDomain deleteById(Long id) {
        return null;
    }
}
