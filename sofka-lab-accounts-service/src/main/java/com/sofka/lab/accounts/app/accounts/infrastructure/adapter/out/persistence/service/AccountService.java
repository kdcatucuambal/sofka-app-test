package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.service;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<AccountModel> findAll();

    AccountModel findById(Long id);

    AccountModel findByAccountNumber(String accountNumber);

    AccountModel save(AccountModel accountModel);

    AccountModel update(Long id, AccountModel accountModel);

    Long deleteById(Long id);

}
