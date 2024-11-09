package com.sofka.lab.customers.app.infrastructure.adapters.configuration;


import com.sofka.lab.customers.app.application.business_logic.contract.*;
import com.sofka.lab.customers.app.application.business_logic.impl.*;
import com.sofka.lab.customers.app.domain.ports.out.event.ProducerClient;
import com.sofka.lab.customers.app.domain.ports.out.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.sofka.lab.customers.app")
public class CustomersConfiguration {


    @Bean
    public BLCreateCustomer blCreateCustomer(CustomerRepository customerRepository) {
        return new BLCreateCustomerImpl(customerRepository);
    }

    @Bean
    public BLDeleteByIdCustomer blDeleteCustomer(CustomerRepository customerRepository) {
        return new BLDeleteByIdCustomerImpl(customerRepository);
    }

    @Bean
    public BLFindAllCustomer blFindAllCustomer(CustomerRepository customerRepository) {
        return new BLFindAllCustomerImpl(customerRepository);
    }

    @Bean
    public BLFindByIdCustomer blFindByIdCustomer(CustomerRepository customerRepository) {
        return new BLFindByIdCustomerImpl(customerRepository);
    }

    @Bean
    public BLFindByIdentificationCustomer blFindByIdentificationCustomer(CustomerRepository customerRepository) {
        return new BLFindByIdentificationCustomerImpl(customerRepository);
    }

    @Bean
    public BLUpdateCustomer blUpdateCustomer(CustomerRepository customerRepository) {
        return new BLUpdateCustomerImpl(customerRepository);
    }

    @Bean
    public BLSendCustomerEvent blSendCustomerEvent(ProducerClient producerClient) {
        return new BLSendCustomerEventImpl(producerClient);
    }



}
