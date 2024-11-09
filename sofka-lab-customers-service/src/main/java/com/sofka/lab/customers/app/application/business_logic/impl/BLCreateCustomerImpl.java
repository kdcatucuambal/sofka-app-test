package com.sofka.lab.customers.app.application.business_logic.impl;

import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.customers.app.application.business_logic.contract.BLCreateCustomer;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.domain.ports.out.repository.CustomerRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class BLCreateCustomerImpl implements BLCreateCustomer {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerModel create(CustomerModel customerModel) {
        validateIdentification(customerModel.getIdentification());
        return this.customerRepository.save(customerModel);
    }


    private void validateIdentification(String identification) {
        var existCustomer = this.customerRepository.existsByIdentification(identification);
        if (existCustomer) throw new BusinessLogicException(3001);
    }


}
