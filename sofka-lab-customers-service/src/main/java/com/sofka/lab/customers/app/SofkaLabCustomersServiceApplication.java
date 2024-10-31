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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4010")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                        .allowedHeaders("x-api-key", "Content-Type", "Authorization")
                        .allowCredentials(true);
            }
        };
    }


}
