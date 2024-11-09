package com.sofka.lab.customers.app.application.business_logic.impl;

import com.sofka.lab.customers.app.application.business_logic.contract.BLSendCustomerEvent;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.domain.ports.out.event.ProducerClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BLSendCustomerEventImpl implements BLSendCustomerEvent {

    private final ProducerClient client;

    @Override
    public void sendEvent(CustomerModel customerModel) {
        validateCustomer(customerModel);
        client.sendCustomerMessage(customerModel);
    }

    private void validateCustomer(CustomerModel customerModel) {
        if (customerModel == null) {
            throw new IllegalArgumentException("CustomerModel can not be null");
        }
    }


}
