package com.sofka.lab.accounts.app.models.service.accounts.methods;

import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entities.AccountEntity;

public interface AccountCreator {

    AccountEntity createAccount(AccountDto accountDto);


}
