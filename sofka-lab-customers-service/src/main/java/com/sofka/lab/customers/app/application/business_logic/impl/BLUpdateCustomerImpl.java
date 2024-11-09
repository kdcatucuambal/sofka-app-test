package com.sofka.lab.customers.app.application.business_logic.impl;

import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.customers.app.application.business_logic.contract.BLUpdateCustomer;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.domain.ports.out.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class BLUpdateCustomerImpl implements BLUpdateCustomer {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerModel update(Long id, CustomerModel customerModel) {
        customerModel.setId(id);
        var customerFound = customerRepository.findById(id).orElseThrow(() -> new BusinessLogicException(3000));;
        setNewData(customerFound, customerModel);
        log.info("Customer updated: {}", customerFound);
        customerRepository.update(customerFound);
        return customerFound;
    }


    private void setNewData(CustomerModel customerFound, CustomerModel customer) {
        customerFound.setPhone(customer.getPhone() != null ? customer.getPhone() : customerFound.getPhone());
        customerFound.setName(customer.getName() != null ? customer.getName() : customerFound.getName());
        customerFound.setAddress(customer.getAddress() != null ? customer.getAddress() : customerFound.getAddress());
        customerFound.setAge(customer.getAge() != null ? customer.getAge() : customerFound.getAge());
        customerFound.setGenre(customer.getGenre() != null ? customer.getGenre() : customerFound.getGenre());
        customerFound.setStatus(customer.getStatus() != null ? customer.getStatus() : customerFound.getStatus());
    }
}
