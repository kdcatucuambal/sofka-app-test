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
public class DebitStrategy implements TransactionStrategy {

    private final AccountMapper accountMapper;

    private final String type = "DBT";

    public DebitStrategy(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public TransactionEntity process(TransactionEntity movement, AccountDto account) {
        log.info("Processing deposit movement: {}", movement.getAmount() + " to account: " + account.getNumber());
        if (movement.getAmount().compareTo(BigDecimal.valueOf(1.0)) < 0 ||
                movement.getAmount().compareTo(BigDecimal.valueOf(5000.0)) > 0) {
            log.error("The transaction amount is incorrect.");
            throw new BusinessLogicException("El monto mínimo para debitar es de $1 y el máximo es de $5,000.", "202");
        }
        BigDecimal newBalance = account.getAvailableBalance().subtract(movement.getAmount());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            log.error("The account does not have enough balance to make the deposit");
            throw new BusinessLogicException("El saldo es insuficiente para la transacción.", "201");
        }
        movement.setBalance(newBalance);
        movement.setType(this.getType());
        movement.setDate(LocalDateTime.now());
        movement.setAmount(movement.getAmount().negate());
        account.setAvailableBalance(newBalance);
        movement.setAccount(accountMapper.toEntity(account));
        log.info("Deposit movement processed successfully");
        return movement;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
