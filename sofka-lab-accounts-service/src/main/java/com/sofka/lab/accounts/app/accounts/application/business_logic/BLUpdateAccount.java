package com.sofka.lab.accounts.app.accounts.application.business_logic;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;

public interface BLUpdateAccount {

    AccountModel update(Long id, AccountModel accountModel);

}
