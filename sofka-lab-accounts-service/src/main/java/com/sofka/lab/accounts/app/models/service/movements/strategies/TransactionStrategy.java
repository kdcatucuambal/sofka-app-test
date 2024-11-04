package com.sofka.lab.accounts.app.models.service.movements.strategies;

import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;

public interface TransactionStrategy {

    TransactionEntity process(TransactionEntity movementEntity, AccountDto accountDto);
    String getType();

}
