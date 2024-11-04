package com.sofka.lab.accounts.app.models.service.accounts.methods;

import com.sofka.bank.objects.Account;
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
        try {
            Account.TypeEnum accountType = Account.TypeEnum.valueOf(type);

            if (accountType == Account.TypeEnum.CTE) {
                return checkingAccount;
            } else if (accountType == Account.TypeEnum.AHO) {
                return savingAccount;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid account type: " + type);
        }

        throw new IllegalArgumentException("Invalid account type: " + type);
    }


}
