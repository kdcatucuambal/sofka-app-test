package com.sofka.lab.accounts.app.models.service.accounts.methods;


import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entity.AccountEntity;
import com.sofka.lab.accounts.app.models.service.utils.UtilService;
import com.sofka.lab.accounts.app.utils.Constants;
import com.sofka.lab.common.exceptions.BusinessLogicException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Slf4j
@Service
public class CheckingAccount implements AccountCreator {

    private final UtilService utilService;


    public CheckingAccount( UtilService utilService) {
        this.utilService = utilService;
    }

    @Override
    public AccountEntity createAccount(AccountDto accountDto) {
        log.info("[Init] Creating a checking account: {}", accountDto.getType());
        //El saldo mínimo de apertura de una cuenta corriente es de 10.00
        if (accountDto.getInitBalance().compareTo(BigDecimal.TEN) < 0) {
            //TODO: Check out the error code
            throw new BusinessLogicException(1003);
        }

        AccountEntity accountEntity = new AccountEntity();
        Long id = utilService.getSequence(Constants.ACCOUNT_SEQUENCE);
        String accountNumber = Constants.fillWithZeros(id.toString(), 6);
        accountEntity.setId(id);
        accountEntity.setNumber(accountNumber);
        accountEntity.setInitBalance(accountDto.getInitBalance());
        accountEntity.setStatus(true);
        accountEntity.setType(accountDto.getType());
        accountEntity.setCustomerId(accountDto.getCustomer().getId());
        accountEntity.setBalance(accountDto.getInitBalance());
        log.info("[End] Checking account created successfully");
        return accountEntity;

    }



}
