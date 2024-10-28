package com.sofka.lab.accounts.app.models.valid;

import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.service.AccountService;
import com.sofka.lab.common.dtos.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagamentAccountServiceImpl implements ManagementAccountService {

    private final AccountService accountService;

    public ManagamentAccountServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public AccountPSTRs execAccountPSTRq(AccountPSTRq accountPSTRq) {
        var accountRq = accountPSTRq.getAccount();
        var customerRq = accountPSTRq.getAccount().getCustomer();
        //TODO: Validate customer data
        AccountDto accountDto = new AccountDto();
        accountDto.setType(accountRq.getType());
        accountDto.setNumber(accountRq.getNumber()); //TODO: Generate acc number
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerRq.getId());
        accountDto.setCustomer(customerDto);
        accountDto = accountService.save(accountDto);
        AccountPSTRs accountPSTRs = new AccountPSTRs();
        Account accountRs = new Account();
        accountRs.setId(accountDto.getId());
        return accountPSTRs;
    }

    @Override
    public AccountPTCRs execAccountPTCRq(AccountPTCRq accountPTCRq) {
        return null;
    }

    @Override
    public AccountGETAllRs execAccountGETAllRq() {
        AccountDto<AccountDto> accounts = this.accountService.findAll();
        AccountGETAllRs accountGETAllRs = new AccountGETAllRs();



        return null;
    }
}
