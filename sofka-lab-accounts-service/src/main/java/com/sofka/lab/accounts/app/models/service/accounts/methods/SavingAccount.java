package com.sofka.lab.accounts.app.models.service.accounts.methods;

import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entities.AccountEntity;
import com.sofka.lab.accounts.app.models.service.accounts.mappers.AccountMapper;
import com.sofka.lab.accounts.app.models.service.utils.UtilService;
import com.sofka.lab.accounts.app.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SavingAccount implements AccountCreator {

    private final UtilService utilService;
    private final AccountMapper accountMapper;

    public SavingAccount(UtilService utilService, AccountMapper accountMapper) {
        this.utilService = utilService;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountEntity createAccount(AccountDto accountDto) {
        log.info("[Init] Creating a saving account: {}", accountDto.getType());
        Long id = utilService.getSequence(Constants.ACCOUNT_SEQUENCE);
        String accountNumber = Constants.fillWithZeros(id.toString(), 10);
        log.info("account number generated: {}", accountNumber);
        accountDto.setId(id);
        accountDto.setNumber(accountNumber);
        log.info("[End] Saving account created successfully");
        return accountMapper.toEntity(accountDto);
    }
}
