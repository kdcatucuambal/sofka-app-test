package com.sofka.lab.accounts.app.accounts.domain.ports.out;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    List<AccountModel> findAll();

    Optional<AccountModel> findById(Long id);

    Optional<AccountModel> findByAccountNumber(String accountNumber);

    AccountModel save(AccountModel accountModel);

    AccountModel update(AccountModel accountModel);

    Long deleteById(Long id);


}
