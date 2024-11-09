package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.service.impl;

import com.sofka.lab.accounts.app.accounts.application.business_logic.*;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.service.AccountService;
import com.sofka.lab.accounts.app.transactions.application.business_logic.BLCreateOpeningTransaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {


    private final BLCreateAccount blCreateAccount;
    private final BLDeleteByIdAccount blDeleteByIdAccount;
    private final BLFindAllAccount blFindAllAccount;
    private final BLUpdateAccount blUpdateAccount;
    private final BLFindByIdAccount blFindByIdAccount;
    private final BLFindByNumberAccount blFindByNumberAccount;
    private final BLCreateOpeningTransaction blCreateOpeningTransaction;


    @Override
    public List<AccountModel> findAll() {
        return blFindAllAccount.findAll();
    }

    @Override
    public AccountModel findById(Long id) {
        return blFindByIdAccount.find(id);
    }

    @Override
    public AccountModel findByAccountNumber(String accountNumber) {
        return blFindByNumberAccount.find(accountNumber);
    }

    @Override
    public AccountModel save(AccountModel accountModel) {
        var accountCreated = blCreateAccount.create(accountModel);
        blCreateOpeningTransaction.create(accountCreated);
        return accountCreated;
    }

    @Override
    public AccountModel update(Long id, AccountModel accountModel) {
        return blUpdateAccount.update(id, accountModel);
    }

    @Override
    public Long deleteById(Long id) {
        return blDeleteByIdAccount.delete(id);
    }
}
