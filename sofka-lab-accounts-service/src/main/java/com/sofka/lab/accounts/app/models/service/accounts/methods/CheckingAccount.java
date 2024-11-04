package com.sofka.lab.accounts.app.models.service.accounts.methods;


import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entities.AccountEntity;
import com.sofka.lab.accounts.app.models.service.accounts.mappers.AccountMapper;
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
    private final AccountMapper accountMapper;


    public CheckingAccount(UtilService utilService, AccountMapper accountMapper) {
        this.utilService = utilService;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountEntity createAccount(AccountDto accountDto) {
        log.info("[Init] Creating a checking account: {}", accountDto.getType());
        if (accountDto.getInitBalance().compareTo(BigDecimal.TEN) < 0) throw new BusinessLogicException(1003);
        Long id = utilService.getSequence(Constants.ACCOUNT_SEQUENCE);
        String accountNumber = Constants.fillWithZeros(id.toString(), 6);
        accountDto.setId(id);
        accountDto.setNumber(accountNumber);
        log.info("[End] Checking account created successfully");
        return accountMapper.toEntity(accountDto);

    }


}
