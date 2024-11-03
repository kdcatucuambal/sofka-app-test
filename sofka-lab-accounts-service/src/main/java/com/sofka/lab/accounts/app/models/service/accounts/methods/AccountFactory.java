package com.sofka.lab.accounts.app.models.service.accounts.methods;

import org.springframework.stereotype.Component;

@Component
public class AccountFactory {


    private final CheckingAccount checkingAccount;
    private final SavingAccount savingAccount;


    public AccountFactory(CheckingAccount checkingAccount, SavingAccount savingAccount) {
        this.checkingAccount = checkingAccount;
        this.savingAccount = savingAccount;
    }

    public AccountCreator getCreatorAccount(String type) {
        if (type.equals("CTE")) {
            return checkingAccount;
        } else if (type.equals("AHO")) {
            return savingAccount;
        }
        throw new IllegalArgumentException("Invalid account type");
    }


}
