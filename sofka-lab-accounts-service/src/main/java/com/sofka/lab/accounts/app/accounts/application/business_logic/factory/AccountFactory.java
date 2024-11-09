package com.sofka.lab.accounts.app.accounts.application.business_logic.factory;

import com.sofka.lab.accounts.app.accounts.application.business_logic.constant.AccountConstant;
import com.sofka.lab.accounts.app.accounts.application.business_logic.factory.impl.CheckingAccount;
import com.sofka.lab.accounts.app.accounts.application.business_logic.factory.impl.SavingAccount;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountFactory {

    private final CheckingAccount checkingAccount;
    private final SavingAccount savingAccount;


    public CreateAccount getCreatorAccount(String type) {
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
