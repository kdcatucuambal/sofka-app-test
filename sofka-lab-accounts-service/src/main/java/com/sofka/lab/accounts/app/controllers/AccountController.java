package com.sofka.lab.accounts.app.controllers;

import com.sofka.bank.objects.AccountPSTRq;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entity.AccountEntity;
import com.sofka.lab.accounts.app.models.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountPSTRq save(@RequestBody AccountPSTRq accountPSTRq) {
        var account = accountPSTRq.getAccount();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(account.getBalance());
        accountEntity.setType(account.getType());

        return accountService.save(accountDto);
    }

    @GetMapping
    public List<AccountDto> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @GetMapping("/by-account-number/{accountNumber}")
    public AccountDto findByAccountNumber(@PathVariable String accountNumber) {
        return accountService.findByNumber(accountNumber);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto update(@RequestBody AccountDto accountDto, @PathVariable Long id) {
        accountDto.setId(id);
        return accountService.update(accountDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }

}
