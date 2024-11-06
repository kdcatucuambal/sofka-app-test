package com.sofka.lab.accounts.app.accounts.application.port.out;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;

import java.util.List;
import java.util.Optional;

public interface AccountPersistencePort {

    List<AccountDomain> findAll();
    Optional<AccountDomain> findById();

    Optional<AccountDomain> findByNumber();
    AccountDomain save (AccountDomain accountDomain);

    AccountDomain update (AccountDomain update);

    void deleteById(Long id);


}
