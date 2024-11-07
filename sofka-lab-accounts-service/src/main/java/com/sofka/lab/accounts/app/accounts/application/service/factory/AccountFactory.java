package com.sofka.lab.accounts.app.accounts.application.service.factory;


import com.sofka.lab.accounts.app.accounts.application.service.factory.impl.CheckingAccount;
import com.sofka.lab.accounts.app.accounts.application.service.factory.impl.SavingAccount;
import com.sofka.lab.accounts.app.accounts.application.service.util.AccountConstant;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AccountFactory {

    private final CheckingAccount checkingAccount;
    private final SavingAccount savingAccount;


    public AccountCreator getCreatorAccount(String type) {
        try {
            if (AccountConstant.CHECKING_ACCOUNT_TYPE.equals(type)) {
                return checkingAccount;
            } else if (AccountConstant.SAVING_ACCOUNT_TYPE.equals(type)) {
                return savingAccount;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid account type: " + type);
        }

        throw new IllegalArgumentException("Invalid account type: " + type);
    }


}
