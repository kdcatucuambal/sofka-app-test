package com.sofka.lab.accounts.app.clients;

import com.sofka.bank.objects.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestAdapterImpl implements CustomerRestAdapter {

    private final CustomerRest customerRest;

    public CustomerRestAdapterImpl(CustomerRest customerRest) {
        this.customerRest = customerRest;
    }

    public Customer findById(Long id) {
        return customerRest.findById(id).getCustomer();
    }

    public Customer findByIdentification(String customerIdentification) {
        return customerRest.findByIdentification(customerIdentification).getCustomer();
    }


}
