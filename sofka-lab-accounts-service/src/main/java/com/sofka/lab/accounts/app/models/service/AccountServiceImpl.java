package com.sofka.lab.accounts.app.models.service;

import com.sofka.bank.objects.Customer;
import com.sofka.lab.accounts.app.clients.CustomerRestAdapter;
import com.sofka.lab.accounts.app.models.dao.AccountDao;
import com.sofka.lab.accounts.app.models.dao.MovementDao;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entity.AccountEntity;
import com.sofka.lab.accounts.app.models.entity.MovementEntity;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final CustomerRestAdapter customerRest;
    private final MovementDao movementDao;

    public AccountServiceImpl(AccountDao accountDao, CustomerRestAdapter customerRest, MovementDao movementDao) {
        this.accountDao = accountDao;
        this.customerRest = customerRest;
        this.movementDao = movementDao;
    }


    @Override
    public AccountDto save(AccountDto accountDto) {
        Customer customerDto;
        Long id = accountDto.getCustomer().getId();
        if (id != null) {
            customerDto = customerRest.findById(id);
        } else {
            customerDto = customerRest.findByIdentification(accountDto.getCustomer().getIdentification());
        }

        if (customerDto == null) {
            throw new BusinessLogicException("El cliente asociado no existe.", "100");
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setNumber(accountDto.getNumber());//TODO: This should be generated
        accountEntity.setInitBalance(accountDto.getInitBalance());
        accountEntity.setStatus(accountDto.getStatus()); //TODO: This should be generated, by default is true
        accountEntity.setType(accountDto.getType());
        accountEntity.setCustomerId(customerDto.getId());
        accountEntity.setBalance(accountDto.getInitBalance());
        accountEntity = accountDao.save(accountEntity);
        accountDto.setId(accountEntity.getId());

        MovementEntity movementEntity = new MovementEntity();
        movementEntity.setAccount(accountEntity);
        movementEntity.setType("APERTURA");
        movementEntity.setBalance(accountEntity.getInitBalance());
        movementEntity.setAmount(accountEntity.getInitBalance());
        movementEntity.setDate(LocalDateTime.now());
        movementDao.save(movementEntity);

        return accountDto;
    }

    @Override
    public AccountDto findById(Long id) {
        AccountEntity cuenta = accountDao.findById(id).orElse(null);
        if (cuenta == null) {
            throw new BusinessLogicException("No existe la cuenta con el id proporcionado: " + id, "101");
        }
        return cuenta.toDto();
    }


    @Override
    public AccountDto findByNumber(String accountNumber) {
        AccountEntity cuenta = accountDao.findByNumber(accountNumber);
        if (cuenta == null) {
            throw new BusinessLogicException("No existe la cuenta con el número proporcionado: "
                    + accountNumber, "102");
        }
        return cuenta.toDto();
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        Optional<AccountEntity> accountDbOptional = accountDao.findById(accountDto.getId());

        if (accountDbOptional.isEmpty()) {
            throw new BusinessLogicException("No existe la cuenta con el id proporcionado: " + accountDto.getId(), "101");
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
    public List<AccountDto> findAll() {
        return accountDao.findByStatusTrue().stream().map(AccountEntity::toDto).toList();
    }

    @Override
    public void updateBalance(String number, BigDecimal balance) {
        AccountEntity account = this.accountDao.findByNumber(number);
        account.setBalance(balance);
        accountDao.save(account);
    }


    @Override
    public void delete(Long id) {
        var accountEntity = this.accountDao.findById(id).orElseThrow(
                () -> new BusinessLogicException("No existe la cuenta con el id proporcionado: " + id, "101")
        );
        accountEntity.setStatus(false);
        accountDao.save(accountEntity);
    }
}
