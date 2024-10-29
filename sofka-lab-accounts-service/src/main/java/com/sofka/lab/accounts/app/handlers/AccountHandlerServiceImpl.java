package com.sofka.lab.accounts.app.handlers;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.service.AccountService;
import com.sofka.lab.common.dtos.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AccountHandlerServiceImpl implements AccountHandlerService {
    private final AccountService accountService;

    public AccountHandlerServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public AccountGETAllRs execAccountGETAll() {
        var accounts = new ArrayList<Account>();
        this.accountService.findAll().forEach(accountDto -> {
            accounts.add(mapAccount(accountDto));
        });
        return new AccountGETAllRs(accounts);
    }

    @Override
    public AccountGETByAccountNumberRs execAccountGETByAccountNumber(String accountNumber) {
        AccountDto accountDto = this.accountService.findByNumber(accountNumber);
        return new AccountGETByAccountNumberRs(mapAccount(accountDto));
    }

    @Override
    public AccountGETByCodeRs execAccountGETByCodeRs(Long code) {
        AccountDto accountDto = this.accountService.findById(code);
        return new AccountGETByCodeRs(mapAccount(accountDto));
    }

    @Override
    public AccountPSTRs execAccountPST(AccountPSTRq accountPSTRq) {
        var accountRq = accountPSTRq.getAccount();
        var accountDto = new AccountDto();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(accountRq.getCustomer().getId());
        accountDto.setCustomer(customerDto);
        accountDto.setNumber(accountRq.getNumber()); //TODO: Number should be generated
        accountDto.setType(accountRq.getType());
        accountDto.setStatus(accountRq.getStatus());
        accountDto.setInitBalance(accountRq.getInitBalance());
        accountDto.setAvailableBalance(accountRq.getBalance());
        accountDto = this.accountService.save(accountDto);
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
        var accountDto = new AccountDto();
        accountDto.setType(accountRq.getType());
        accountDto.setStatus(accountRq.getStatus());
        accountDto.setId(accountRq.getId());
        accountDto = this.accountService.update(accountDto);
        return new AccountPTCRs(Account.builder().id(accountDto.getId()).build());
    }

    private Account mapAccount(AccountDto accountDto) {
        var customer = Customer.builder().id(accountDto.getId()).build();
        return Account.builder()
                .id(accountDto.getId())
                .balance(accountDto.getAvailableBalance())
                .number(accountDto.getNumber())
                .status(accountDto.getStatus())
                .type(accountDto.getType())
                .initBalance(accountDto.getInitBalance())
                .customer(customer)
                .build();
    }

}
