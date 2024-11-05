package com.sofka.lab.accounts.app.models.service.movements.strategies;

import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;
import com.sofka.lab.accounts.app.models.service.accounts.mappers.AccountMapper;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class CreditStrategy implements TransactionStrategy {


    private final AccountMapper accountMapper;

    private final String type = "CRE";

    public CreditStrategy(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public TransactionEntity process(TransactionEntity movement, AccountDto account) {
        log.info("Processing credit movement: {}", movement.getAmount() + " to account: " + account.getNumber());
        if (movement.getAmount().compareTo(BigDecimal.valueOf(0.5)) < 0
                || movement.getAmount().compareTo(BigDecimal.valueOf(10000.0)) > 0) {
            log.error("The transaction amount is incorrect.");
            throw new BusinessLogicException(2005);
        }
        BigDecimal newBalance = account.getAvailableBalance().add(movement.getAmount());
        movement.setBalance(newBalance);
        movement.setType(this.getType());
        movement.setDate(LocalDateTime.now());
        account.setAvailableBalance(newBalance);
        movement.setAccount(accountMapper.toEntity(account));
        log.info("Credit movement processed successfully");
        return movement;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
