package com.sofka.lab.accounts.app.accounts.application.service.account.factory.impl;

import com.sofka.lab.accounts.app.accounts.application.service.account.factory.AccountCreator;
import com.sofka.lab.accounts.app.accounts.application.service.account.util.AccountConstant;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class SavingAccount implements AccountCreator {


    @Override
    public AccountDomain createAccount(Long seq, AccountDomain accountDomain) {
        if (accountDomain.getInitBalance().compareTo(BigDecimal.ZERO) < 0) throw new BusinessLogicException(1005);
        log.info("[Init] Creating a saving account: {}", accountDomain.getType());
        String accountNumber = AccountConstant.fillWithZeros(seq.toString(), 10);
        log.info("account number generated: {}", accountNumber);
        accountDomain.setId(seq);
        accountDomain.setNumber(accountNumber);
        log.info("[End] Saving account validated successfully");
        return accountDomain;
    }


}
