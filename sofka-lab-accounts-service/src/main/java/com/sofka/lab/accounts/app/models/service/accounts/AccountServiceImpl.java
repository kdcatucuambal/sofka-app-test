package com.sofka.lab.accounts.app.models.service.accounts;

import com.sofka.bank.objects.Customer;
import com.sofka.bank.objects.Transaction;
import com.sofka.lab.accounts.app.clients.CustomerRestAdapter;
import com.sofka.lab.accounts.app.models.dao.AccountDao;
import com.sofka.lab.accounts.app.models.dao.MovementDao;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entity.AccountEntity;
import com.sofka.lab.accounts.app.models.entity.MovementEntity;
import com.sofka.lab.accounts.app.models.service.accounts.methods.AccountCreator;
import com.sofka.lab.accounts.app.models.service.accounts.methods.AccountFactory;
import com.sofka.lab.common.dtos.CustomerDto;
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

    public AccountServiceImpl(AccountDao accountDao, CustomerRestAdapter customerRest, MovementDao movementDao,
                              AccountFactory accountFactory) {
        this.accountDao = accountDao;
        this.customerRest = customerRest;
        this.movementDao = movementDao;
        this.accountFactory = accountFactory;
    }


    @Override
    @Transactional
    public AccountDto save(AccountDto accountDto) {
        Customer customerDto = getCustomer(accountDto.getCustomer());
        log.info("Customer found: {}", customerDto);
        if (customerDto == null) {
            throw new BusinessLogicException(1000);
        }
        accountDto.getCustomer().setId(customerDto.getId());
        log.info("Creating account: {}", accountDto);
        AccountEntity accountEntity = accountFactory.getCreatorAccount(accountDto.getType()).createAccount(accountDto);
        accountDao.save(accountEntity);
        MovementEntity movementEntity = new MovementEntity();
        movementEntity.setAccount(accountEntity);
        movementEntity.setType(Transaction.TypeEnum.CRE.getValue());
        movementEntity.setBalance(accountEntity.getInitBalance());
        movementEntity.setAmount(accountEntity.getInitBalance());
        movementEntity.setDate(LocalDateTime.now());
        movementDao.save(movementEntity);
        accountDto.setId(accountEntity.getId());
        return accountDto;
    }


    private Customer getCustomer(CustomerDto customerDto) {
        return customerDto.getId() != null
                ? customerRest.findById(customerDto.getId())
                : customerRest.findByIdentification(customerDto.getIdentification());
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto findById(Long id) {
        AccountEntity cuenta = accountDao.findById(id).orElse(null);
        if (cuenta == null) {
            throw new BusinessLogicException(1001);
        }
        return cuenta.toDto();
    }


    @Override
    @Transactional(readOnly = true)
    public AccountDto findByNumber(String accountNumber) {
        AccountEntity cuenta = accountDao.findByNumber(accountNumber);
        if (cuenta == null) {
            throw new BusinessLogicException(1002);
        }
        return cuenta.toDto();
    }

    @Override
    @Transactional
    public AccountDto update(AccountDto accountDto) {
        Optional<AccountEntity> accountDbOptional = accountDao.findById(accountDto.getId());

        if (accountDbOptional.isEmpty()) {
            throw new BusinessLogicException(1001);
        }

        AccountEntity accountDb = accountDbOptional.get();
        //cuentaDb.setSaldo(cuenta.getSaldoDisponible());
        //accountDb.setNumber(accountDto.getNumber()); //TODO: Should'n be updated
        accountDb.setType(accountDto.getType());
        accountDb.setStatus(accountDto.getStatus());
        accountDao.save(accountDb);
        return accountDto;
    }


    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> findAll() {
        return accountDao.findByStatusTrue().stream().map(AccountEntity::toDto).toList();
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
        var accountEntity = this.accountDao.findById(id).orElseThrow(
                () -> new BusinessLogicException(1001)
        );
        accountEntity.setStatus(false);
        accountDao.save(accountEntity);
    }
}
