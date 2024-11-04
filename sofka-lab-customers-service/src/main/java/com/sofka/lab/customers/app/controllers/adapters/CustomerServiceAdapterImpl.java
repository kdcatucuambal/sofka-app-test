package com.sofka.lab.customers.app.controllers.adapters;

import com.sofka.bank.objects.*;
import com.sofka.lab.customers.app.models.entity.dtos.CustomerDto;
import com.sofka.lab.customers.app.models.services.CustomerService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerServiceAdapterImpl implements CustomerServiceAdapter {


    private final CustomerService customerService;
    private final CustomerControllerMapper customerMapper;

    public CustomerServiceAdapterImpl(CustomerService customerService, CustomerControllerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerGETAllRs execCustomerGETAll() {
        List<Customer> customers = customerService.findAll()
                .stream()
                .map(customerMapper::toCustomer)
                .collect(Collectors.toList());
        return new CustomerGETAllRs(customers);
    }

    @Override
    public CustomerPSTRs execCustomerPST(CustomerPSTRq customerPSTRq) {
        var customerRq = customerPSTRq.getCustomer();
        var customerDto = this.customerService.save(customerMapper.toCustomerEntity(customerRq));
        var customer = Customer.builder().id(customerDto.getId()).build();
        return new CustomerPSTRs(customer);
    }

    @Override
    public CustomerPTCRs execCustomerPTC(CustomerPTCRq customerPTCRq) {
        var customerRq = customerPTCRq.getCustomer();
        var customerUpdated = this.customerService.update(customerMapper.toCustomerDto(customerRq));
        return new CustomerPTCRs(Customer.builder().id(customerUpdated.getId()).build());
    }

    @Override
    public CustomerGETByIdentificationRs execCustomerGETByIdentification(String identification) {
        CustomerDto customerDto = this.customerService.findByIdentification(identification);
        return new CustomerGETByIdentificationRs(customerMapper.toCustomer(customerDto));
    }


    @Override
    public CustomerGETByCodeRs execCustomerGETByCode(Long code) {
        CustomerDto customerDto = this.customerService.findById(code);
        return new CustomerGETByCodeRs(customerMapper.toCustomer(customerDto));
    }

    @Override
    public CustomerDELRs execCustomerDEL(Long code) {
        this.customerService.delete(code);
        return new CustomerDELRs(Customer.builder().id(code).build());
    }

}
