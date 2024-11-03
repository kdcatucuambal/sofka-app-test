package com.sofka.lab.accounts.app.models.service.accounts.methods;

import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entity.AccountEntity;
import com.sofka.lab.accounts.app.models.service.utils.UtilService;
import com.sofka.lab.accounts.app.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SavingAccount implements AccountCreator {

    private final UtilService utilService;

    public SavingAccount(UtilService utilService) {
        this.utilService = utilService;
    }

    @Override
    public AccountEntity createAccount(AccountDto accountDto) {
        log.info("[Init] Creating a saving account: {}", accountDto.getType());
        log.info("Creating account: {}", accountDto);
        AccountEntity accountEntity = new AccountEntity();
        Long id = utilService.getSequence(Constants.ACCOUNT_SEQUENCE);
        log.info("id generated: {}", id);
        String accountNumber = Constants.fillWithZeros(id.toString(), 10);
        log.info("account number generated: {}", accountNumber);
        accountEntity.setId(id);
        accountEntity.setNumber(accountNumber);
        accountEntity.setInitBalance(accountDto.getInitBalance());
        accountEntity.setStatus(true);
        accountEntity.setType(accountDto.getType());
        accountEntity.setCustomerId(accountDto.getCustomer().getId());
        accountEntity.setBalance(accountDto.getInitBalance());
        log.info("[End] Saving account created successfully");
        return accountEntity;
    }
}
