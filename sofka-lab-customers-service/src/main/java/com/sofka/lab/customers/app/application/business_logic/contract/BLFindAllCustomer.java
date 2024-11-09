package com.sofka.lab.customers.app.application.business_logic.contract;

import com.sofka.lab.customers.app.domain.model.CustomerModel;

import java.util.List;

public interface BLFindAllCustomer {

    List<CustomerModel> findAll();

}
