package com.sofka.lab.accounts.app.controllers;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.handlers.AccountHandlerService;
import com.sofka.lab.accounts.app.handlers.validators.AccountValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountHandlerService accountService;
    private final AccountValidator accountValidator;




    public AccountController(AccountHandlerService accountService, AccountValidator accountValidator) {
        this.accountService = accountService;
        this.accountValidator = accountValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(accountValidator);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountPSTRs accountPST(@RequestBody AccountPSTRq accountPSTRq, BindingResult result) {
        System.out.println(result.getTarget().getClass().toString());
        accountValidator.validate(accountPSTRq, result);
        System.out.println("I'M ERROR: " + result.hasErrors());
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println("ERROR: " + error.getDefaultMessage());
            });
        }
        return accountService.execAccountPST(accountPSTRq);
    }

    @GetMapping
    public AccountGETAllRs accountGETAll() {
        return accountService.execAccountGETAll();
    }

    @GetMapping("/{id}")
    public AccountGETByCodeRs accountGETByCode(@PathVariable Long id) {
        return accountService.execAccountGETByCodeRs(id);
    }

    @GetMapping("/by-account-number/{accountNumber}")
    public AccountGETByAccountNumberRs accountGETByAccountNumber(@PathVariable String accountNumber) {
        return accountService.execAccountGETByAccountNumber(accountNumber);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountPTCRs accountPTC(@RequestBody AccountPTCRq accountPTCRq, @PathVariable Long id) {
        accountPTCRq.getAccount().setId(id);
        return accountService.execAccountPTC(accountPTCRq);
    }

    @DeleteMapping("/{id}")
    public AccountDELRs delete(@PathVariable Long id) {
        return this.accountService.execAccountDEL(id);
    }


}
