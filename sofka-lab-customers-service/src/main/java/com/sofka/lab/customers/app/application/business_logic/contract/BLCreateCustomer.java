package com.sofka.lab.customers.app.application.business_logic.contract;

import com.sofka.lab.customers.app.domain.model.CustomerModel;

public interface BLCreateCustomer {

    CustomerModel create(CustomerModel customerModel);

}
