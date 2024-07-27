package com.sofka.lab.accounts.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
//@EntityScan({"com.lab.springboot.app.commons.models.entity"})
public class SofkaLabAccountsServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(SofkaLabAccountsServiceApplication.class, args);
    }

}
