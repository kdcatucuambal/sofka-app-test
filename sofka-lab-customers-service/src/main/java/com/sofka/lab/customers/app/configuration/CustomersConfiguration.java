package com.sofka.lab.customers.app.configuration;

import com.sofka.lab.common.exceptions.models.BusinessToHttpErrorImpl;
import com.sofka.lab.common.exceptions.models.interfaces.BusinessToHttpError;
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

}