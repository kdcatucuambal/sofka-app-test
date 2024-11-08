package com.sofka.lab.customers.app.application.port.out;

import com.sofka.lab.common.domain.model.CustomerEvent;

public interface CustomerEventPort {

    void sendPayload(CustomerEvent customerEvent);

}
