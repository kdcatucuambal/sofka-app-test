package com.sofka.lab.customers.app.domain.ports.out.event;

import com.sofka.lab.customers.app.domain.model.CustomerModel;

public interface ProducerClient {

    void sendCustomerMessage(CustomerModel customerModel);

}
