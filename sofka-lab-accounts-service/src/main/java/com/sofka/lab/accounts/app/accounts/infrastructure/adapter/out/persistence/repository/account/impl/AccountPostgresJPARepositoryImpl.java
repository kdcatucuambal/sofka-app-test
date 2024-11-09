package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.repository.account.impl;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.mapper.AccountMapper;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.repository.account.AccountJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountPostgresJPARepositoryImpl implements AccountRepository {

    private final AccountJPARepository accountRepository;
    private final AccountMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<AccountModel> findAll() {
        return accountRepository.findAllByStatusTrue().stream().map(mapper::toAccountModel).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountModel> findById(Long id) {
        return accountRepository.findById(id).map(mapper::toAccountModel);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountModel> findByAccountNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber).map(mapper::toAccountModel);
    }

    @Transactional
    @Override
    public AccountModel save(AccountModel accountModel) {
        return mapper.toAccountModel(accountRepository.save(mapper.toAccountEntity(accountModel)));
    }

    @Transactional
    @Override
    public AccountModel update(AccountModel accountModel) {
        return mapper.toAccountModel(accountRepository.save(mapper.toAccountEntity(accountModel)));
    }

    @Transactional
    @Override
    public Long deleteById(Long id) {
        accountRepository.findById(id).ifPresent(accountEntity -> {
            accountEntity.setStatus(Boolean.FALSE);
            accountRepository.save(accountEntity);
        });
        return id;
    }
}
