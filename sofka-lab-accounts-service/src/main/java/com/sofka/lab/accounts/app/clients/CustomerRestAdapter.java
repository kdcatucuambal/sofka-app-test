package com.sofka.lab.accounts.app.clients;

import com.sofka.bank.objects.Customer;

public interface CustomerRestAdapter {

    Customer findById(Long id);
    Customer findByIdentification(String customerIdentification);

}
