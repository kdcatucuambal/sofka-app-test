package com.sofka.lab.customers.app.infrastructure.adapters.in.rest.adapter;

import com.sofka.bank.objects.*;
import com.sofka.lab.customers.app.application.port.in.CustomerServicePort;
import com.sofka.lab.customers.app.infrastructure.adapters.in.rest.mapper.CustomerRestMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomerRestAdapterImpl implements CustomerRestAdapter {

    private final CustomerServicePort customerService;
    private final CustomerRestMapper customerRestMapper;

    @Override
    public CustomerGETAllRs execCustomerGETAll() {
        var customers = customerService.findAll().stream().map(customerRestMapper::toCustomer).toList();
        return CustomerGETAllRs.builder().customers(customers).build();

    }

    @Override
    public CustomerPSTRs execCustomerPST(CustomerPSTRq customerPSTRs) {
        var customer = customerRestMapper.toCustomerDomain(customerPSTRs.getCustomer());
        customer = customerService.save(customer);
        return CustomerPSTRs.builder().customer(Customer.builder().id(customer.getId()).build()).build();
    }

    @Override
    public CustomerPTCRs execCustomerPTC(Long id, CustomerPTCRq customerPTCRq) {
        var customer = customerService.update(id, customerRestMapper.toCustomerDomain(customerPTCRq.getCustomer()));
        return CustomerPTCRs.builder().customer(Customer.builder().id(customer.getId()).build()).build();
    }

    @Override
    public CustomerGETByIdentificationRs execCustomerGETByIdentification(String identification) {
        var customerFound = customerService.findByIdentification(identification);
        return CustomerGETByIdentificationRs.builder().customer(customerRestMapper.toCustomer(customerFound)).build();
    }

    @Override
    public CustomerGETByCodeRs execCustomerGETByCode(Long code) {
        var customerFound = customerService.findById(code);
        return CustomerGETByCodeRs.builder().customer(customerRestMapper.toCustomer(customerFound)).build();
    }

    @Override
    public CustomerDELRs execCustomerDEL(Long code) {
        customerService.deleteById(code);
        return CustomerDELRs.builder().customer(Customer.builder().id(code).build()).build();
    }
}
