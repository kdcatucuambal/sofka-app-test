package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.service;

import com.sofka.lab.customers.app.domain.model.CustomerModel;

import java.util.List;


public interface CustomerService {

    CustomerModel save(CustomerModel customerModel);
    CustomerModel update(Long id, CustomerModel customerModel);
    List<CustomerModel> findAll();
    CustomerModel findById(Long id);
    CustomerModel findByIdentification(String identification);
    void deleteById(Long id);

}
