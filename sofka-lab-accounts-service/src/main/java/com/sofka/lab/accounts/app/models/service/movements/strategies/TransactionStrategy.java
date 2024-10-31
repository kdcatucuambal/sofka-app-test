package com.sofka.lab.accounts.app.models.service.movements.strategies;

import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.entity.MovementEntity;

public interface TransactionStrategy {

    MovementEntity process(MovementEntity movementEntity, AccountDto accountDto);
    String getType();

}
