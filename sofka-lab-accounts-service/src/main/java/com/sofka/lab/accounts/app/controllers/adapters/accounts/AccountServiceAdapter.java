package com.sofka.lab.accounts.app.controllers.adapters.accounts;

import com.sofka.bank.objects.*;

public interface AccountServiceAdapter {

    AccountGETAllRs execAccountGETAll();

    AccountGETByAccountNumberRs execAccountGETByAccountNumber(String accountNumber);

    AccountGETByCodeRs execAccountGETByCodeRs(Long code);

    AccountPSTRs execAccountPST(AccountPSTRq accountPSTRq);

    AccountDELRs execAccountDEL(Long code);

    AccountPTCRs execAccountPTC(AccountPTCRq accountPTCRq);

}
