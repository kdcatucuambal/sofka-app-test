package com.sofka.lab.customers.app.controllers;

import com.sofka.lab.common.dtos.CustomerDto;
import com.sofka.lab.customers.app.models.entity.CustomerEntity;
import com.sofka.lab.customers.app.models.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDto findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/by-identification/{customerIdentification}")
    public CustomerDto findByIdentification(@PathVariable String customerIdentification ) {
        return customerService.findByIdentification(customerIdentification);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto save(@RequestBody CustomerEntity customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto update(@RequestBody CustomerDto cliente, @PathVariable Long id) {
        cliente.setId(id);
        return customerService.update(cliente);
    }

}
