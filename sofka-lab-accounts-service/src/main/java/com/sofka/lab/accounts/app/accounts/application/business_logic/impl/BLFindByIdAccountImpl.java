package com.sofka.lab.accounts.app.accounts.application.business_logic.impl;

import com.sofka.lab.accounts.app.accounts.application.business_logic.BLFindByIdAccount;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BLFindByIdAccountImpl implements BLFindByIdAccount {
    private final AccountRepository accountRepository;

    @Override
    public AccountModel find(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new BusinessLogicException(1001));
    }
}
