package com.sofka.lab.accounts.app.clients;

import com.sofka.lab.common.models.dtos.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sofka-lab-customers-service")
public interface ClienteRest {


    @GetMapping("/clientes/{id}")
    ClienteDto findById(@PathVariable Long id);


    @GetMapping("/clientes/identificacion/{identificacion}")
    ClienteDto findByIdentificacion(@PathVariable String identificacion);

}
