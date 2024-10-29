package com.sofka.lab.accounts.app.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sofka.lab.common.exceptions.models.BusinessToHttpErrorImpl;
import com.sofka.lab.common.exceptions.models.interfaces.BusinessToHttpError;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;

import java.time.format.DateTimeFormatter;


@Configuration
public class AccountsConfiguration {
    private static final String dateFormat = "yyyy/MM/dd";
    private static final String dateTimeFormat = "yyyy/MM/dd HH:mm:ss";

    /**
     * THis method is used to create a new instance of BusinessToHttpFacade,
     * and map the business error codes to http status
     *
     * @return BusinessToHttpFacade
     */
    @Bean
    @Primary
    public BusinessToHttpError httpCodeMapping() {
        var businessToHttpErrorMapping = new BusinessToHttpErrorImpl();

        businessToHttpErrorMapping.addNewMapping("100", HttpStatus.NOT_FOUND);
        businessToHttpErrorMapping.addNewMapping("101", HttpStatus.NOT_FOUND);
        businessToHttpErrorMapping.addNewMapping("102", HttpStatus.NOT_FOUND);

        businessToHttpErrorMapping.addNewMapping("200", HttpStatus.NOT_FOUND);
        businessToHttpErrorMapping.addNewMapping("202", HttpStatus.NOT_FOUND);
        return businessToHttpErrorMapping;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }




}
