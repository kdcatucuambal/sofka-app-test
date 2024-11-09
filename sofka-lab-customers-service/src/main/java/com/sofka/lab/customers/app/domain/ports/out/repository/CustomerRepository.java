package com.sofka.lab.customers.app.domain.ports.out.repository;

import com.sofka.lab.customers.app.domain.model.CustomerModel;

import java.util.List;
import java.util.Optional;

//PUERTO DE SALIDA
public interface CustomerRepository {

    List<CustomerModel> findAll();

    Optional<CustomerModel> findById(Long id);

    Optional<CustomerModel> findByIdentification(String identification);

    CustomerModel save(CustomerModel customer);

    void update(CustomerModel customer);

    void deleteById(Long id);

    boolean existsByIdentification(String identification);

    boolean existsById(Long id);

}
