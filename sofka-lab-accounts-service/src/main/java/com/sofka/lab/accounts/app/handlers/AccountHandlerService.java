package com.sofka.lab.accounts.app.handlers;

import com.sofka.bank.objects.*;

public interface AccountHandlerService {
    AccountGETAllRs execAccountGETAll();

    AccountGETByAccountNumberRs execAccountGETByAccountNumber(String accountNumber);

    AccountGETByCodeRs execAccountGETByCodeRs(Long code);

    AccountPSTRs execAccountPST(AccountPSTRq accountPSTRq);

    AccountDELRs execAccountDEL(Long code);

    AccountPTCRs execAccountPTC(AccountPTCRq accountPTCRq);

}
