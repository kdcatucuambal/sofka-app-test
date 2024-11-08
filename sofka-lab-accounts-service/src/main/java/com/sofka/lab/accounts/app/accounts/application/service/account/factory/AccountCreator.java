package com.sofka.lab.accounts.app.accounts.application.service.account.factory;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;

public interface AccountCreator {

    AccountDomain createAccount(Long seq, AccountDomain accountDomain);


}
