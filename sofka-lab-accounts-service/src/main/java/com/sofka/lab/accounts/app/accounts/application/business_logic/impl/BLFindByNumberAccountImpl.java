package com.sofka.lab.accounts.app.accounts.application.business_logic.impl;

import com.sofka.lab.accounts.app.accounts.application.business_logic.BLFindByNumberAccount;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BLFindByNumberAccountImpl implements BLFindByNumberAccount {

    private final AccountRepository accountRepository;

    @Override
    public AccountModel find(String number) {
        return accountRepository.findByAccountNumber(number).orElseThrow(() -> new BusinessLogicException(1002));
    }
}
