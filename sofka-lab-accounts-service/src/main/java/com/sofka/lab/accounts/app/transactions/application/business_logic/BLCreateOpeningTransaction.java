package com.sofka.lab.accounts.app.transactions.application.business_logic;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;

public interface BLCreateOpeningTransaction {

    TransactionModel create(AccountModel accountModel);

}
