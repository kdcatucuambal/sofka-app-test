package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.models.dtos.AccountDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountDto save(AccountDto accountDto);

    AccountDto findById(Long id);

    AccountDto findByNumber(String number);

    AccountDto update(AccountDto accountDto);

    List<AccountDto> findAll();

    void updateBalance(String number, BigDecimal amount);

    void delete(Long id);


}
