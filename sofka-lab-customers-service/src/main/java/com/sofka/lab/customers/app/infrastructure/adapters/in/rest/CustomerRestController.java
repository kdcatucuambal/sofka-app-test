package com.sofka.lab.customers.app.infrastructure.adapters.in.rest;


import com.sofka.bank.objects.*;
import com.sofka.lab.customers.app.infrastructure.adapters.in.rest.adapter.CustomerRestAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/customers")
public class CustomerRestController {


    private final CustomerRestAdapter customerRestService;

    @GetMapping
    public CustomerGETAllRs customerGETAll() {
        return customerRestService.execCustomerGETAll();
    }

    @GetMapping("/{id}")
    public CustomerGETByCodeRs customerGETByCode(@PathVariable Long id) {
        return customerRestService.execCustomerGETByCode(id);
    }

    @GetMapping("/by-identification/{customerIdentification}")
    public CustomerGETByIdentificationRs customerGETByIdentification(@PathVariable String customerIdentification) {
        return customerRestService.execCustomerGETByIdentification(customerIdentification);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerPSTRs customerPST(@RequestBody CustomerPSTRq customer) {
        return customerRestService.execCustomerPST(customer);
    }

    @DeleteMapping("/{id}")
    public CustomerDELRs customerDEL(@PathVariable Long id) {
        return this.customerRestService.execCustomerDEL(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerPTCRs customerPTC(@RequestBody CustomerPTCRq customerPTCRq, @PathVariable Long id) {
        return customerRestService.execCustomerPTC(id, customerPTCRq);
    }


}
