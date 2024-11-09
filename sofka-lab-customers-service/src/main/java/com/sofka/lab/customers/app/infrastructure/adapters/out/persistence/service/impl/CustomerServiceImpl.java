package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.service.impl;

import com.sofka.lab.customers.app.application.business_logic.contract.*;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final BLCreateCustomer blCreateCustomer;
    private final BLDeleteByIdCustomer blDeleteByIdCustomer;
    private final BLFindAllCustomer blFindAllCustomer;
    private final BLFindByIdCustomer blFindByIdCustomer;
    private final BLFindByIdentificationCustomer blFindByIdentificationCustomer;
    private final BLUpdateCustomer blUpdateCustomer;
    private final BLSendCustomerEvent blSendCustomerEvent;

    @Override
    public CustomerModel save(CustomerModel customerModel) {
        var customerCreated = blCreateCustomer.create(customerModel);
        blSendCustomerEvent.sendEvent(customerCreated);
        return customerCreated;
    }

    @Override
    public CustomerModel update(Long id, CustomerModel customerModel) {
        var customerUpdated = blUpdateCustomer.update(id, customerModel);
        blSendCustomerEvent.sendEvent(customerUpdated);
        return customerUpdated;
    }

    @Override
    public List<CustomerModel> findAll() {
        return blFindAllCustomer.findAll();
    }

    @Override
    public CustomerModel findById(Long id) {
        return blFindByIdCustomer.find(id);
    }

    @Override
    public CustomerModel findByIdentification(String identification) {
        return blFindByIdentificationCustomer.find(identification);
    }

    @Override
    public void deleteById(Long id) {
        blDeleteByIdCustomer.delete(id);
        var customerFound = blFindByIdCustomer.find(id);
        //customerFound.setStatus(false);
        blSendCustomerEvent.sendEvent(customerFound);
    }
}
