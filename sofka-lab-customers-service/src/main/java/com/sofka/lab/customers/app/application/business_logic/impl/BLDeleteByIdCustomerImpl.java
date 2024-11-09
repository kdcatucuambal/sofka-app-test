package com.sofka.lab.customers.app.application.business_logic.impl;

import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.customers.app.application.business_logic.contract.BLDeleteByIdCustomer;
import com.sofka.lab.customers.app.domain.ports.out.repository.CustomerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BLDeleteByIdCustomerImpl implements BLDeleteByIdCustomer {

    private final CustomerRepository customerRepository;

    @Override
    public Long delete(Long id) {
        this.validateBeforeDelete(id);
        this.customerRepository.deleteById(id);
        return id;
    }

    private void validateBeforeDelete(Long id) {
        var customer = this.customerRepository
                .findById(id).orElseThrow(() -> new BusinessLogicException(3000));
        if (!customer.getStatus()) throw new BusinessLogicException(3002);
    }

}
