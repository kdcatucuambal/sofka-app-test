package com.sofka.lab.accounts.app.models.valid;

import com.sofka.bank.objects.*;

public interface ManagementAccountService {

    /**
     * Method to execute the account PSTR. This creates a new account.
     * @return AccountPSTRs
     */
    AccountPSTRs execAccountPSTRq(AccountPSTRq accountPSTRq);

    /**
     * Method to execute the account PTCR. This updates an account.
     * @param accountPTCRq
     * @return
     */
    AccountPTCRs execAccountPTCRq(AccountPTCRq accountPTCRq);

    /**
     * Method to execute the account DEL. This get all accounts.
     * @return
     */
    AccountGETAllRs execAccountGETAllRq();

}
