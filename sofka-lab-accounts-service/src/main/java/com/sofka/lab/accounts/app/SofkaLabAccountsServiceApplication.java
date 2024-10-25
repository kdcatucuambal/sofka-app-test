package com.sofka.lab.accounts.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.sofka.lab.common.*"}) // Escanea ambos proyectos
public class SofkaLabAccountsServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SofkaLabAccountsServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("SofkaLabAccountsServiceApplication.run method executed");
    }
}
