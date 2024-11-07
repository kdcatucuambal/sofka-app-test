package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.in.rest.adapter;

import com.sofka.bank.objects.*;

public interface AccountRestAdapter  {

    AccountGETAllRs execAccountGETAll();

    AccountGETByAccountNumberRs execAccountGETByAccountNumber(String accountNumber);

    AccountGETByCodeRs execAccountGETByCodeRs(Long code);

    AccountPSTRs execAccountPST(AccountPSTRq accountPSTRq);

    AccountDELRs execAccountDEL(Long code);

    AccountPTCRs execAccountPTC(AccountPTCRq accountPTCRq);

}
