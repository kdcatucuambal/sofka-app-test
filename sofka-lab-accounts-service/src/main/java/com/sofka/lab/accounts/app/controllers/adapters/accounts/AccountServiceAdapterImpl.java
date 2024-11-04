package com.sofka.lab.accounts.app.controllers.adapters.accounts;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.controllers.adapters.mappers.AccountControllerMapper;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.service.accounts.AccountService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AccountServiceAdapterImpl implements AccountServiceAdapter {

    private final AccountService accountService;

    private final AccountControllerMapper accountMapper;

    public AccountServiceAdapterImpl(AccountService accountService, AccountControllerMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountGETAllRs execAccountGETAll() {
        var accounts = new ArrayList<Account>();
        this.accountService.findAll().forEach(accountDto -> accounts.add(accountMapper.toAccount(accountDto)));
        return new AccountGETAllRs(accounts);
    }

    @Override
    public AccountGETByAccountNumberRs execAccountGETByAccountNumber(String accountNumber) {
        AccountDto accountDto = this.accountService.findByNumber(accountNumber);
        return new AccountGETByAccountNumberRs(accountMapper.toAccount(accountDto));
    }

    @Override
    public AccountGETByCodeRs execAccountGETByCodeRs(Long code) {
        AccountDto accountDto = this.accountService.findById(code);
        return new AccountGETByCodeRs(accountMapper.toAccount(accountDto));
    }

    @Override
    public AccountPSTRs execAccountPST(AccountPSTRq accountPSTRq) {
        var accountRq = accountPSTRq.getAccount();
        var accountDto = this.accountService.save(accountMapper.toAccountDto(accountRq));
        return new AccountPSTRs(Account.builder().id(accountDto.getId()).build());
    }


    @Override
    public AccountDELRs execAccountDEL(Long code) {
        this.accountService.delete(code);
        return new AccountDELRs(Account.builder().id(code).build());
    }

    @Override
    public AccountPTCRs execAccountPTC(AccountPTCRq accountPTCRq) {
        var accountRq = accountPTCRq.getAccount();
        var accountDto = AccountDto.builder()
                .id(accountRq.getId())
                .type(accountRq.getType().getValue())
                .status(accountRq.getStatus())
                .build();
        accountDto = this.accountService.update(accountDto);
        return new AccountPTCRs(Account.builder().id(accountDto.getId()).build());
    }


}
