package com.sofka.lab.customers.app.controllers;

import com.sofka.bank.objects.*;
import com.sofka.lab.customers.app.extras.MessageService;
import com.sofka.lab.customers.app.handlers.CustomerHandlerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerHandlerService customerService;

    private final MessageService messageService;


    public CustomerController(CustomerHandlerService customerService, MessageService messageService) {
        this.customerService = customerService;
        this.messageService = messageService;
    }

    @GetMapping
    public CustomerGETAllRs customerGETAll() {
        System.out.println(this.messageService.getMessages());
        return this.customerService.execCustomerGETAll();
    }

    @GetMapping("/{id}")
    public CustomerGETByCodeRs customerGETByCode(@PathVariable Long id) {
        return customerService.execCustomerGETByCode(id);
    }

    @GetMapping("/by-identification/{customerIdentification}")
    public CustomerGETByIdentificationRs customerGETByIdentification(@PathVariable String customerIdentification) {
        return customerService.execCustomerGETByIdentification(customerIdentification);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerPSTRs customerPST(@RequestBody CustomerPSTRq customer) {
        return customerService.execCustomerPST(customer);
    }

    @DeleteMapping("/{id}")
    public CustomerDELRs customerDEL(@PathVariable Long id) {
        return this.customerService.execCustomerDEL(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerPTCRs customerPTC(@RequestBody CustomerPTCRq customerPTCRq, @PathVariable Long id) {
        customerPTCRq.getCustomer().setId(id);
        return customerService.execCustomerPTC(customerPTCRq);
    }


}
