package com.sofka.lab.accounts.app.clients;

import com.sofka.bank.objects.CustomerGETByCodeRs;
import com.sofka.bank.objects.CustomerGETByIdentificationRs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sofka-lab-customers-service")
public interface CustomerRest {

    @GetMapping("/customers/{id}")
    CustomerGETByCodeRs findById(@PathVariable Long id);

    @GetMapping("/customers/by-identification/{customerIdentification}")
    CustomerGETByIdentificationRs findByIdentification(@PathVariable String customerIdentification);

}
