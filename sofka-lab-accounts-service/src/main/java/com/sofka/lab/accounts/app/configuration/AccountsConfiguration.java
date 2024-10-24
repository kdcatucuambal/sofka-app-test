package com.sofka.lab.accounts.app.configuration;

import com.sofka.lab.common.exceptions.models.BusinessToHttpErrorImpl;
import com.sofka.lab.common.exceptions.models.interfaces.BusinessToHttpFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


@Configuration
public class AccountsConfiguration {


    /**
     * THis method is used to create a new instance of BusinessToHttpFacade,
     * and map the business error codes to http status
     * @return BusinessToHttpFacade
     */
    @Bean
    public BusinessToHttpFacade httpCodeMapping() {
        var businessToHttpErrorMapping = new BusinessToHttpErrorImpl();
        businessToHttpErrorMapping.addNewMapping("100", HttpStatus.NOT_FOUND);
        businessToHttpErrorMapping.addNewMapping("101", HttpStatus.NOT_FOUND);
        businessToHttpErrorMapping.addNewMapping("102", HttpStatus.NOT_FOUND);
        return businessToHttpErrorMapping;
    }


}
