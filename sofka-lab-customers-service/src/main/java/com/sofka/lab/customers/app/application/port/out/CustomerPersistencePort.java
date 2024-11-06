package com.sofka.lab.customers.app.application.port.out;

import com.sofka.lab.customers.app.domain.model.CustomerDomain;

import java.util.List;
import java.util.Optional;

public interface CustomerPersistencePort {

    List<CustomerDomain> findAll();

    Optional<CustomerDomain> findById(Long id);

    Optional<CustomerDomain> findByIdentification(String identification);

    CustomerDomain save(CustomerDomain customer);

    void update(CustomerDomain customer);

    void deleteById(Long id);

    boolean existsByIdentification(String identification);

    boolean existsById(Long id);


}
