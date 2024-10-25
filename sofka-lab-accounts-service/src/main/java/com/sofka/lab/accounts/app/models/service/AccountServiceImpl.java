package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.clients.CustomerRest;
import com.sofka.lab.accounts.app.models.dao.AccountDao;
import com.sofka.lab.accounts.app.models.dao.MovementDao;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entity.Account;
import com.sofka.lab.accounts.app.models.entity.Movement;
import com.sofka.lab.common.dtos.CustomerDto;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    private final AccountDao accountDao;
    private final CustomerRest customerRest;
    private final MovementDao movementDao;

    public AccountServiceImpl(AccountDao accountDao, CustomerRest customerRest, MovementDao movementDao) {
        this.accountDao = accountDao;
        this.customerRest = customerRest;
        this.movementDao = movementDao;
    }


    @Override
    public AccountDto save(AccountDto accountDto) {
        CustomerDto customerDto;
        Long id = accountDto.getCustomer().getId();
        if (id != null) {
            customerDto = customerRest.findById(id);
        } else {
            customerDto = customerRest.findByIdentification(accountDto.getCustomer().getIdentification());
        }

        if (customerDto == null) {
            throw new BusinessLogicException("El cliente asociado no existe.", "100");
        }

        Account account = new Account();
        account.setNumber(accountDto.getNumber());
        account.setInitBalance(accountDto.getInitBalance());
        account.setStatus(accountDto.getStatus());
        account.setType(accountDto.getType());
        account.setCustomerId(customerDto.getId());
        account.setBalance(accountDto.getAvailableBalance());
        account = accountDao.save(account);
        accountDto.setId(account.getId());

        Movement movement = new Movement();
        movement.setAccount(account);
        movement.setType("APERTURA");
        movement.setBalance(account.getInitBalance());
        movement.setAmount(account.getInitBalance());
        movement.setDate(new Date());
        movementDao.save(movement);

        return accountDto;
    }

    @Override
    public AccountDto findById(Long id) {
        Account cuenta = accountDao.findById(id).orElse(null);
        if (cuenta == null) {
            throw new BusinessLogicException("No existe la cuenta con el id proporcionado: " + id, "101");
        }
        return cuenta.toDto();
    }


    @Override
    public AccountDto findByNumber(String accountNumber) {
        Account cuenta = accountDao.findByNumber(accountNumber);
        if (cuenta == null) {
            throw new BusinessLogicException("No existe la cuenta con el número proporcionado: "
                    + accountNumber, "102");
        }
        return cuenta.toDto();
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        Optional<Account> accountDbOptional = accountDao.findById(accountDto.getId());

        if (accountDbOptional.isEmpty()) {
            throw new BusinessLogicException("No existe la cuenta con el id proporcionado: " + accountDto.getId(), "101");
        }

        Account accountDb = accountDbOptional.get();
        //cuentaDb.setSaldo(cuenta.getSaldoDisponible());
        accountDb.setNumber(accountDto.getNumber());
        accountDb.setType(accountDto.getType());
        accountDb.setStatus(accountDto.getStatus());
        accountDao.save(accountDb);
        return accountDto;
    }


    @Override
    public List<AccountDto> findAll() {
        return accountDao.findAll().stream().map(Account::toDto).toList();
    }

    @Override
    public void updateBalance(String number, BigDecimal balance) {
        Account account = this.accountDao.findByNumber(number);
        account.setBalance(balance);
        accountDao.save(account);
    }


    @Override
    public void delete(Long id) {
        //TODO: Delete logical database
        accountDao.deleteById(id);
    }
}
