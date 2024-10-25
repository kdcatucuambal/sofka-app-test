package com.sofka.lab.accounts.app.clients;

import com.sofka.lab.common.dtos.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sofka-lab-customers-service")
public interface CustomerRest {

    @GetMapping("/customers/{id}")
    CustomerDto findById(@PathVariable Long id);

    @GetMapping("/customers/by-identification/{customerIdentification}")
    CustomerDto findByIdentification(@PathVariable String customerIdentification);

}
