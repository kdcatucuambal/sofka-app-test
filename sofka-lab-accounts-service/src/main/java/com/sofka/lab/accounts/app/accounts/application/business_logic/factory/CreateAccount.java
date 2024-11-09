package com.sofka.lab.accounts.app.accounts.application.business_logic.factory;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;

public interface CreateAccount {
    AccountModel create(Long seq, AccountModel accountDomain);
}
