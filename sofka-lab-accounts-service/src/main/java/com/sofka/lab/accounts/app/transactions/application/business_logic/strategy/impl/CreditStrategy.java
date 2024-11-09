package com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.impl;

import com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.TransactionStrategy;
import com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.constant.ConstantTransaction;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Slf4j
public class CreditStrategy implements TransactionStrategy {

    @Override
    public TransactionModel process(TransactionModel transaction, BigDecimal currentBalance) {
        if (transaction.getAmount().compareTo(ConstantTransaction.MIN_AMOUNT_CRE) < 0
                || transaction.getAmount().compareTo(ConstantTransaction.MAX_AMOUNT_CRE) > 0) {
            log.error("The transaction amount is incorrect.");
            throw new BusinessLogicException(2005);
        }

        BigDecimal newBalance = currentBalance.add(transaction.getAmount());
        transaction.setBalance(newBalance);
        transaction.setType(this.getType());
        transaction.setDate(LocalDateTime.now());
        return transaction;
    }

    @Override
    public String getType() {
        return ConstantTransaction.CREDIT_TYPE;
    }

}
