package com.sofka.lab.accounts.app.accounts.domain.ports.out;

import com.sofka.lab.common.domain.model.CustomerEvent;

import java.util.Optional;

public interface CustomerQueryRepository {

    Long save(CustomerEvent customerEvent);

    Long update(CustomerEvent customerEvent);

    Optional<CustomerEvent> findById(Long id);

    Optional<CustomerEvent> findByIdentification(String identification);


}
