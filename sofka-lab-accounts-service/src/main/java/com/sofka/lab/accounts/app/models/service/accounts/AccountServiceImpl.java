package com.sofka.lab.accounts.app.models.service.accounts;

import com.sofka.bank.objects.Customer;
import com.sofka.bank.objects.Transaction;
import com.sofka.lab.accounts.app.clients.CustomerRestAdapter;
import com.sofka.lab.accounts.app.models.dao.AccountDao;
import com.sofka.lab.accounts.app.models.dao.MovementDao;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.dtos.CustomerDto;
import com.sofka.lab.accounts.app.models.entities.AccountEntity;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;
import com.sofka.lab.accounts.app.models.service.accounts.mappers.AccountMapper;
import com.sofka.lab.accounts.app.models.service.accounts.methods.AccountFactory;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final CustomerRestAdapter customerRest;
    private final MovementDao movementDao;
    private final AccountFactory accountFactory;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountDao accountDao, CustomerRestAdapter customerRest, MovementDao movementDao,
                              AccountFactory accountFactory, AccountMapper accountMapper) {
        this.accountDao = accountDao;
        this.customerRest = customerRest;
        this.movementDao = movementDao;
        this.accountFactory = accountFactory;
        this.accountMapper = accountMapper;
    }


    @Override
    @Transactional
    public AccountDto save(AccountDto accountDto) {
        Customer customerDto = getCustomer(accountDto.getCustomerDto());
        if (customerDto == null) throw new BusinessLogicException(1000);
        accountDto.getCustomerDto().setId(customerDto.getId());
        accountDto.setAvailableBalance(accountDto.getInitBalance());
        AccountEntity accountEntity = accountFactory
                .getCreatorAccount(accountDto.getType())
                .createAccount(accountDto);
        accountDao.save(accountEntity);
        TransactionEntity movementEntity = new TransactionEntity();
        movementEntity.setAccount(accountEntity);
        movementEntity.setType(Transaction.TypeEnum.CRE.getValue());
        movementEntity.setBalance(accountEntity.getInitBalance());
        movementEntity.setAmount(accountEntity.getInitBalance());
        movementEntity.setDate(LocalDateTime.now());
        movementDao.save(movementEntity);
        accountDto.setId(accountEntity.getId());
        return accountDto;
    }


    @Override
    @Transactional(readOnly = true)
    public AccountDto findById(Long id) {
        AccountEntity account = accountDao.findById(id).orElseThrow(() -> new BusinessLogicException(1001));
        return accountMapper.toDto(account);
    }


    @Override
    @Transactional(readOnly = true)
    public AccountDto findByNumber(String accountNumber) {
        AccountEntity account = accountDao.findByNumber(accountNumber);
        if (account == null) {
            throw new BusinessLogicException(1002);
        }
        return accountMapper.toDto(account);
    }

    @Override
    @Transactional
    public AccountDto update(AccountDto accountDto) {
        AccountEntity account = accountDao.findById(accountDto.getId()).orElseThrow(() -> new BusinessLogicException(1001));
        account.setType(accountDto.getType());
        account.setStatus(accountDto.getStatus());
        accountDao.save(account);
        return accountDto;
    }


    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> findAll() {
        return accountDao.findByStatusTrue().stream().map(accountMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void updateBalance(String number, BigDecimal balance) {
        AccountEntity account = this.accountDao.findByNumber(number);
        account.setBalance(balance);
        accountDao.save(account);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        var accountEntity = this.accountDao.findById(id).orElseThrow(() -> new BusinessLogicException(1001));
        accountEntity.setStatus(false);
        accountDao.save(accountEntity);
    }

    private Customer getCustomer(CustomerDto customerDto) {
        return customerDto.getId() != null
                ? customerRest.findById(customerDto.getId())
                : customerRest.findByIdentification(customerDto.getIdentification());
    }
}
