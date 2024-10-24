package com.sofka.lab.common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class}
)
public class SofkaLabCommonApplication implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        System.out.println("SofkaLabCommonApplication.run method executed");
    }
}
