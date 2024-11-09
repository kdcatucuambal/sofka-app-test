package com.sofka.lab.accounts.app.accounts.application.business_logic.impl;

import com.sofka.lab.accounts.app.accounts.application.business_logic.BLCreateAccount;
import com.sofka.lab.accounts.app.accounts.application.business_logic.factory.AccountFactory;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.CustomerQueryRepository;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.SequenceRepository;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class BLCreateAccountImpl implements BLCreateAccount {

    private final AccountRepository accountRepository;
    private final SequenceRepository sequenceRepository;
    private final CustomerQueryRepository customerQueryRepository;
    private final AccountFactory accountFactory;


    @Override
    public AccountModel create(AccountModel accountModel) {
        validateCustomer(accountModel);
        accountModel = generateAccountFromFactory(accountModel);
        return accountModel;
    }


    private AccountModel generateAccountFromFactory(AccountModel accountModel) {
        Long seq = sequenceRepository.getNextId();
        accountModel = accountFactory.getCreatorAccount(accountModel.getType()).create(seq, accountModel);
        accountModel.setStatus(Boolean.TRUE);
        accountModel.setBalance(accountModel.getInitBalance());
        accountModel = accountRepository.save(accountModel);
        return accountModel;
    }


    private void validateCustomer(AccountModel accountModel) {
        if (accountModel == null) {
            throw new BusinessLogicException(1000);
        }
        var customer = customerQueryRepository.findById(accountModel.getCustomerId())
                .orElseThrow(() -> new BusinessLogicException(1000));
        if (!customer.getStatus()) throw new BusinessLogicException(1006);
    }

}
