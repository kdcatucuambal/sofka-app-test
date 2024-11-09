package com.sofka.lab.accounts.app.accounts.application.business_logic.impl;

import com.sofka.lab.accounts.app.accounts.application.business_logic.BLUpdateAccount;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BLUpdateAccountImpl implements BLUpdateAccount {

    private final AccountRepository accountRepository;

    @Override
    public AccountModel update(Long id, AccountModel accountModel) {
        accountModel.setId(id);
        var accountFound = validateAccountAndGet(accountModel);
        return accountRepository.update(accountFound);
    }

    private AccountModel validateAccountAndGet(AccountModel accountModel) {
        if (accountModel.getId() == null) {
            throw new RuntimeException("El id de la cuenta no puede ser nulo");
        }
        var accountFound = accountRepository.findById(accountModel.getId()).orElseThrow(() -> new BusinessLogicException(1001));
        if (!accountFound.getStatus()) throw new BusinessLogicException(1006);
        accountFound.setType(accountModel.getType() != null ? accountModel.getType() : accountFound.getType());
        accountFound.setStatus(accountModel.getStatus() != null ? accountModel.getStatus() : accountFound.getStatus());
        return accountFound;
    }

}
