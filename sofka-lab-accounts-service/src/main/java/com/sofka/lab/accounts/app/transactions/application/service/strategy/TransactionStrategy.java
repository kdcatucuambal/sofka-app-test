package com.sofka.lab.accounts.app.transactions.application.service.strategy;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionDomain;

import java.math.BigDecimal;

public interface TransactionStrategy {

    TransactionDomain process(TransactionDomain transactionDomain, BigDecimal currentBalance);
    String getType();


}
