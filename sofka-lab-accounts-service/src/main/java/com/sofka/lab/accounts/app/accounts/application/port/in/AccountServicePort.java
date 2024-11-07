package com.sofka.lab.accounts.app.accounts.application.port.in;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;

import java.util.List;

public interface AccountServicePort {

    List<AccountDomain> findAll();

    AccountDomain findById(Long id);

    AccountDomain findByNumber(String number);

    AccountDomain save(AccountDomain accountDomain);

    AccountDomain update(Long id, AccountDomain accountDomain);

    AccountDomain deleteById(Long id);


}
