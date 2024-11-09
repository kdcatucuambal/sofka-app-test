package com.sofka.lab.customers.app.application.business_logic.impl;

import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.customers.app.application.business_logic.contract.BLFindByIdCustomer;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.domain.ports.out.repository.CustomerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BLFindByIdCustomerImpl implements BLFindByIdCustomer {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerModel find(Long id) {
        return this.customerRepository.findById(id).orElseThrow(() -> new BusinessLogicException(3000));
    }
}
