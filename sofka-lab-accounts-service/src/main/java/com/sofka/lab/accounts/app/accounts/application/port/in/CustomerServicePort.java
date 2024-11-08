package com.sofka.lab.accounts.app.accounts.application.port.in;

import com.sofka.lab.common.domain.model.CustomerEvent;

import java.util.Optional;

public interface CustomerServicePort {

    Long save(CustomerEvent customerEvent);

    Long update(CustomerEvent customerEvent);

    CustomerEvent findById(Long id);

    CustomerEvent findByIdentification(String identification);

}
