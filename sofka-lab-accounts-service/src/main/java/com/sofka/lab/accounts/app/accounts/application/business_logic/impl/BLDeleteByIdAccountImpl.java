package com.sofka.lab.accounts.app.accounts.application.business_logic.impl;

import com.sofka.lab.accounts.app.accounts.application.business_logic.BLDeleteByIdAccount;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BLDeleteByIdAccountImpl implements BLDeleteByIdAccount {

    private final AccountRepository accountRepository;

    @Override
    public Long delete(Long id) {
        validateAccountStatus(id);
        return accountRepository.deleteById(id);
    }

    private void validateAccountStatus(Long id) {
        var account = accountRepository.findById(id).orElseThrow(() -> new BusinessLogicException(1001));
        if (!account.getStatus()) throw new BusinessLogicException(1004);
    }
}
