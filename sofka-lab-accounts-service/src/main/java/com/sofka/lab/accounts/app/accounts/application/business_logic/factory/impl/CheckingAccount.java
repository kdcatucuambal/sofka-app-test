package com.sofka.lab.accounts.app.accounts.application.business_logic.factory.impl;

import com.sofka.lab.accounts.app.accounts.application.business_logic.constant.AccountConstant;
import com.sofka.lab.accounts.app.accounts.application.business_logic.factory.CreateAccount;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class CheckingAccount implements CreateAccount {
    @Override
    public AccountModel create(Long seq, AccountModel accountDomain) {
        log.info("[Init] Creating a checking account: {}", accountDomain.getType());
        if (accountDomain.getInitBalance().compareTo(BigDecimal.TEN) < 0) throw new BusinessLogicException(1003);
        String accountNumber = "C-".concat(AccountConstant.fillWithZeros(seq.toString(), 6));
        log.info("account number generated: {}", accountNumber);
        accountDomain.setId(seq);
        accountDomain.setNumber(accountNumber);
        log.info("[End] Checking account validated successfully");
        return accountDomain;
    }
}
