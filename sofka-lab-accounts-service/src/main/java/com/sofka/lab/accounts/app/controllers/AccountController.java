package com.sofka.lab.accounts.app.controllers;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.controllers.adapters.accounts.AccountServiceAdapter;
import com.sofka.lab.accounts.app.controllers.adapters.validators.AccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    private final AccountServiceAdapter accountService;
    private final AccountValidator accountValidator;
    

    public AccountController(AccountServiceAdapter accountService, AccountValidator accountValidator) {
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
        accountValidator.validate(accountPSTRq, result);
        if (result.hasErrors()) {
            log.error("Error in accountPST: {}", result.getAllErrors());
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
