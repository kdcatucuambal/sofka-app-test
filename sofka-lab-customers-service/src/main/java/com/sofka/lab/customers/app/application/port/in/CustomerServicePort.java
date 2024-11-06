package com.sofka.lab.customers.app.application.port.in;

import com.sofka.lab.customers.app.domain.model.CustomerDomain;

import java.util.List;

public interface CustomerServicePort {

    List<CustomerDomain> findAll();

    CustomerDomain findById(Long id);

    CustomerDomain findByIdentification(String identification);

    CustomerDomain save(CustomerDomain customer);

    CustomerDomain update(Long id, CustomerDomain customer);

    void deleteById(Long id);

}
