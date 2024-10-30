package com.sofka.lab.customers.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.lab.customers.app.extras.MessageService;
import com.sofka.lab.customers.app.extras.MessageServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.sofka.lab.common.*"})
public class SofkaLabCustomersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SofkaLabCustomersServiceApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MessageService messageService(ObjectMapper objectMapper) {
        return new MessageServiceImpl(objectMapper);
    }

}
