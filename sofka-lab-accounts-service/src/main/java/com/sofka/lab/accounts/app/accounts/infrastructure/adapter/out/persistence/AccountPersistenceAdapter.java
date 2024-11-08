package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence;

import com.sofka.lab.accounts.app.accounts.application.port.out.AccountPersistencePort;
import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.mapper.AccountMapper;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class AccountPersistenceAdapter implements AccountPersistencePort {


    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional(readOnly = true)
    @Override
    public List<AccountDomain> findAll() {
        return accountRepository.findAllByStatusTrue()
                .stream()
                .map(accountMapper::toAccountDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountDomain> findById(Long id) {
        return accountRepository.findById(id).map(accountMapper::toAccountDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountDomain> findByNumber(String number) {
        return accountRepository.findByNumber(number).map(accountMapper::toAccountDomain);
    }

    @Transactional
    @Override
    public AccountDomain save(AccountDomain accountDomain) {
        var accountEntity = accountMapper.toAccountEntity(accountDomain);
        accountEntity = accountRepository.save(accountEntity);
        return accountMapper.toAccountDomain(accountEntity);
    }

    @Transactional
    @Override
    public AccountDomain update(AccountDomain update) {
        var accountEntity = accountMapper.toAccountEntity(update);
        accountEntity = this.accountRepository.save(accountEntity);
        return accountMapper.toAccountDomain(accountEntity);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        this.accountRepository.updateStatusById(id, Boolean.FALSE);
    }

    @Override
    public Long getSeqForAccount() {
        return this.accountRepository.getSequence();
    }
}
