package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.in.rest;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.in.rest.adapter.AccountRestAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    private final AccountRestAdapter accountService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountPSTRs accountPST(@RequestBody AccountPSTRq accountPSTRq) {
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
