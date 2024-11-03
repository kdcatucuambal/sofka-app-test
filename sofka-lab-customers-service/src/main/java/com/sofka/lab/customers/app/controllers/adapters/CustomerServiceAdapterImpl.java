package com.sofka.lab.customers.app.controllers.adapters;

import com.sofka.bank.objects.*;
import com.sofka.lab.customers.app.models.entity.CustomerEntity;
import com.sofka.lab.customers.app.models.entity.dtos.CustomerDto;
import com.sofka.lab.customers.app.models.services.CustomerService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerServiceAdapterImpl implements CustomerServiceAdapter {


    private final CustomerService customerService;

    public CustomerServiceAdapterImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public CustomerGETAllRs execCustomerGETAll() {
        List<Customer> customers = new ArrayList<>();
        customerService.findAll().forEach(customerDto -> {
            Customer customer = Customer.builder()
                    .id(customerDto.getId())
                    .name(customerDto.getName())
                    .age(customerDto.getAge())
                    .status(customerDto.getStatus())
                    .phone(customerDto.getPhone())
                    .address(customerDto.getAddress())
                    .genre(Customer.GenreEnum.valueOf(customerDto.getGenre()))
                    .identification(customerDto.getIdentification())
                    .build();
            customers.add(customer);
        });
        return new CustomerGETAllRs(customers);
    }

    @Override
    public CustomerPSTRs execCustomerPST(CustomerPSTRq customerPSTRq) {
        var customerRq = customerPSTRq.getCustomer();
        var customerEntity = new CustomerEntity();

        customerEntity.setPassword(customerRq.getPassword());
        customerEntity.setStatus(customerRq.getStatus());
        customerEntity.setAddress(customerRq.getAddress());
        customerEntity.setAge(customerRq.getAge());
        customerEntity.setGenre(customerRq.getGenre().getValue());
        customerEntity.setIdentification(customerRq.getIdentification());
        customerEntity.setName(customerRq.getName());
        customerEntity.setPhone(customerRq.getPhone());

        var customerDto = this.customerService.save(customerEntity);
        System.out.println("CUSTOMER_DTO: " + customerDto.toString());
        var customerPSTRs = new CustomerPSTRs();
        var customer = Customer.builder().id(customerDto.getId()).build();
        customerPSTRs.setCustomer(customer);
        return customerPSTRs;
    }

    @Override
    public CustomerPTCRs execCustomerPTC(CustomerPTCRq customerPTCRq) {
        var customerRq = customerPTCRq.getCustomer();
        CustomerDto customerDto = CustomerDto.builder()
                .id(customerRq.getId())
                .name(customerRq.getName())
                .age(customerRq.getAge())
                .status(customerRq.getStatus())
                .phone(customerRq.getPhone())
                .address(customerRq.getAddress())
                .genre(customerRq.getGenre().getValue())
                .build();
        this.customerService.update(customerDto);
        return new CustomerPTCRs(Customer.builder().id(customerDto.getId()).build());
    }

    @Override
    public CustomerGETByIdentificationRs execCustomerGETByIdentification(String identification) {
        CustomerDto customerDto = this.customerService.findByIdentification(identification);
        return new CustomerGETByIdentificationRs(mapCustomer(customerDto));
    }


    @Override
    public CustomerGETByCodeRs execCustomerGETByCode(Long code) {
        CustomerDto customerDto = this.customerService.findById(code);
        return new CustomerGETByCodeRs(mapCustomer(customerDto));
    }

    @Override
    public CustomerDELRs execCustomerDEL(Long code) {
        this.customerService.delete(code);
        return new CustomerDELRs(Customer.builder().id(code).build());
    }

    private Customer mapCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .age(customerDto.getAge())
                .status(customerDto.getStatus())
                .phone(customerDto.getPhone())
                .address(customerDto.getAddress())
                .genre(Customer.GenreEnum.valueOf(customerDto.getGenre()))
                .identification(customerDto.getIdentification())
                .build();
    }
}
