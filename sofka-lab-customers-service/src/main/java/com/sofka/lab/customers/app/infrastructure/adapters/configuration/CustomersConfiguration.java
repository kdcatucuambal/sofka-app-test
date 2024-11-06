package com.sofka.lab.customers.app.infrastructure.adapters.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.lab.common.exceptions.models.BusinessToHttpErrorImpl;
import com.sofka.lab.common.exceptions.models.interfaces.BusinessToHttpError;
import com.sofka.lab.customers.app.application.port.in.CustomerServicePort;
import com.sofka.lab.customers.app.application.port.out.CustomerPersistencePort;
import com.sofka.lab.customers.app.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;

@Configuration
public class CustomersConfiguration {

    @Bean
    @Primary
    public BusinessToHttpError httpCodeMapping() {
        var businessToHttpErrorMapping = new BusinessToHttpErrorImpl();
        businessToHttpErrorMapping.addNewMapping("300", HttpStatus.NOT_FOUND);
        return businessToHttpErrorMapping;
    }


    @Bean
    public CustomerServicePort customerServicePort(CustomerPersistencePort customerPersistencePort) {
        return new CustomerService(customerPersistencePort);
    }


}
