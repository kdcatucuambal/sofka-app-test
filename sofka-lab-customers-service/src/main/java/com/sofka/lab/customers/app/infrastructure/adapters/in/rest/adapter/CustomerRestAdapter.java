package com.sofka.lab.customers.app.infrastructure.adapters.in.rest.adapter;

import com.sofka.bank.objects.*;

public interface CustomerRestAdapter {

    CustomerGETAllRs execCustomerGETAll();

    CustomerPSTRs execCustomerPST(CustomerPSTRq customerPSTRs);

    CustomerPTCRs execCustomerPTC(Long id, CustomerPTCRq customerPTCRq);

    CustomerGETByIdentificationRs execCustomerGETByIdentification(String identification);

    CustomerGETByCodeRs execCustomerGETByCode(Long code);

    CustomerDELRs execCustomerDEL(Long code);



}
