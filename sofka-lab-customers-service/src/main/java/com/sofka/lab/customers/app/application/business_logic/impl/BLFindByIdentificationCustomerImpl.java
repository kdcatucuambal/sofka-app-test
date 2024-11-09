package com.sofka.lab.customers.app.application.business_logic.impl;

import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.customers.app.application.business_logic.contract.BLFindByIdentificationCustomer;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.domain.ports.out.repository.CustomerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BLFindByIdentificationCustomerImpl implements BLFindByIdentificationCustomer {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerModel find(String identification) {
        return this.customerRepository.findByIdentification(identification)
                .orElseThrow(() -> new BusinessLogicException(3003));
    }
}
