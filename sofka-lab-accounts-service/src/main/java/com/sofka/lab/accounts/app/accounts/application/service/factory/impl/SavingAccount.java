package com.sofka.lab.accounts.app.accounts.application.service.factory.impl;

import com.sofka.lab.accounts.app.accounts.application.service.factory.AccountCreator;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;
import com.sofka.lab.accounts.app.utils.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SavingAccount implements AccountCreator {


    @Override
    public AccountDomain createAccount(Long seq, AccountDomain accountDomain) {
        log.info("[Init] Creating a saving account: {}", accountDomain.getType());
        String accountNumber = Constants.fillWithZeros(seq.toString(), 10);
        log.info("account number generated: {}", accountNumber);
        accountDomain.setId(seq);
        log.info("[End] Saving account validated successfully");
        return accountDomain;
    }


}
