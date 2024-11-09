package com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.impl;

import com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.TransactionStrategy;
import com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.constant.ConstantTransaction;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
public class DebitStrategy implements TransactionStrategy {
    @Override
    public TransactionModel process(TransactionModel transactionDomain, BigDecimal currentBalance) {
        log.info("Processing deposit movement: {}", transactionDomain.getAmount() + " to account: " + transactionDomain.getAccountId());
        if (transactionDomain.getAmount().compareTo(ConstantTransaction.MIN_AMOUNT_DBT) < 0 ||
                transactionDomain.getAmount().compareTo(ConstantTransaction.MAX_AMOUNT_DBT) > 0) {
            log.error("The transaction amount is incorrect.");
            throw new BusinessLogicException(2004);
        }

        BigDecimal newBalance = currentBalance.subtract(transactionDomain.getAmount());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            log.error("The account does not have enough balance to make the deposit");
            throw new BusinessLogicException(2001);
        }

        transactionDomain.setBalance(newBalance);
        transactionDomain.setType(this.getType());
        transactionDomain.setDate(LocalDateTime.now());
        transactionDomain.setAmount(transactionDomain.getAmount().negate());
        return transactionDomain;
    }

    @Override
    public String getType() {
        return ConstantTransaction.DEBIT_TYPE;
    }
}
