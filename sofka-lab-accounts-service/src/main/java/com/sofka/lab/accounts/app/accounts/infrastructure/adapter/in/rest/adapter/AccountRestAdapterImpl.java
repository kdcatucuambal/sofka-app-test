package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.in.rest.adapter;


import com.sofka.bank.objects.*;
import com.sofka.lab.accounts.app.accounts.application.port.in.AccountServicePort;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.in.rest.mapper.AccountRestMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccountRestAdapterImpl implements AccountRestAdapter {

    private final AccountRestMapper accountRestMapper;
    private final AccountServicePort accountService;

    @Override
    public AccountGETAllRs execAccountGETAll() {
        var accounts = accountService.findAll().stream().map(accountRestMapper::toAccount).toList();
        return AccountGETAllRs.builder().accounts(accounts).build();
    }

    @Override
    public AccountGETByAccountNumberRs execAccountGETByAccountNumber(String accountNumber) {
        var account = accountService.findByNumber(accountNumber);
        return AccountGETByAccountNumberRs.builder().account(accountRestMapper.toAccount(account)).build();
    }

    @Override
    public AccountGETByCodeRs execAccountGETByCodeRs(Long code) {
        var account = accountService.findById(code);
        return AccountGETByCodeRs.builder().account(accountRestMapper.toAccount(account)).build();
    }

    @Override
    public AccountPSTRs execAccountPST(AccountPSTRq accountPSTRq) {
        var account = accountRestMapper.toAccountDomain(accountPSTRq.getAccount());
        account = accountService.save(account);
        return AccountPSTRs.builder().account(Account.builder().id(account.getId()).build()).build();
    }

    @Override
    public AccountDELRs execAccountDEL(Long code) {
        var account = accountService.deleteById(code);
        return AccountDELRs.builder().account(Account.builder().id(code).build()).build();
    }

    @Override
    public AccountPTCRs execAccountPTC(AccountPTCRq accountPTCRq) {
        accountService.update(accountPTCRq.getAccount().getId(), accountRestMapper.toAccountDomain(accountPTCRq.getAccount()));
        return AccountPTCRs.builder().account(Account.builder().id(accountPTCRq.getAccount().getId()).build()).build();
    }
}
