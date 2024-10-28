package com.sofka.lab.customers.app.handlers;

import com.sofka.bank.objects.*;

public interface CustomerHandlerService {
    CustomerGETAllRs execCustomerGETAll();

    CustomerPSTRs execCustomerPST(CustomerPSTRq customerPSTRs);

    CustomerPTCRs execCustomerPTC(CustomerPTCRq customerPTCRq);

    CustomerGETByIdentificationRs execCustomerGETByIdentification(String identification);

    CustomerGETByCodeRs execCustomerGETByCode(Long code);

    CustomerDELRs execCustomerDEL(Long code);
}
