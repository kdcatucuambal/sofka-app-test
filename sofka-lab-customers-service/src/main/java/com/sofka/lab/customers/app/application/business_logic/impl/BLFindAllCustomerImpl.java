package com.sofka.lab.customers.app.application.business_logic.impl;

import com.sofka.lab.customers.app.application.business_logic.contract.BLFindAllCustomer;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.domain.ports.out.repository.CustomerRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BLFindAllCustomerImpl implements BLFindAllCustomer {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerModel> findAll() {
        return customerRepository.findAll();
    }
}
