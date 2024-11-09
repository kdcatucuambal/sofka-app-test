package com.sofka.lab.accounts.app.accounts.application.business_logic.impl;

import com.sofka.lab.accounts.app.accounts.application.business_logic.BLFindAllAccount;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BLFindAllAccountImpl implements BLFindAllAccount {
    private final AccountRepository accountRepository;

    @Override
    public List<AccountModel> findAll() {
        return accountRepository.findAll();
    }
}
