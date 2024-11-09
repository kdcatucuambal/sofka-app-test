package com.sofka.lab.accounts.app.transactions.application.business_logic.strategy;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;

import java.math.BigDecimal;

public interface TransactionStrategy {

    TransactionModel process(TransactionModel transactionDomain, BigDecimal currentBalance);
    String getType();


}
