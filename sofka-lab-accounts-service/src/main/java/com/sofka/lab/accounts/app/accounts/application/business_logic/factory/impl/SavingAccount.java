package com.sofka.lab.accounts.app.accounts.application.business_logic.factory.impl;

import com.sofka.lab.accounts.app.accounts.application.business_logic.constant.AccountConstant;
import com.sofka.lab.accounts.app.accounts.application.business_logic.factory.CreateAccount;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class SavingAccount implements CreateAccount {


    @Override
    public AccountModel create(Long seq, AccountModel accountDomain) {
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
