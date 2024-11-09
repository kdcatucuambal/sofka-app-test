package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.configuration;


import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import com.sofka.lab.accounts.app.transactions.application.business_logic.*;
import com.sofka.lab.accounts.app.transactions.application.business_logic.impl.*;
import com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.constant.ConstantTransaction;

import com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.TransactionStrategy;
import com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.impl.CreditStrategy;
import com.sofka.lab.accounts.app.transactions.application.business_logic.strategy.impl.DebitStrategy;

import com.sofka.lab.accounts.app.transactions.domain.port.out.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import java.util.Map;

@Configuration
@ComponentScan("com.sofka.lab.accounts.app.transactions")
@ComponentScan("com.sofka.lab.accounts.app.accounts")
public class TransactionConfiguration {

//
    @Bean
    public Map<String, TransactionStrategy> trnStrategies() {
        return Map.of(
                ConstantTransaction.CREDIT_TYPE, new CreditStrategy(),
                ConstantTransaction.DEBIT_TYPE, new DebitStrategy()
        );
    }

    @Bean
    public BLCreateOpeningTransaction blCreateOpeningTransaction(TransactionRepository transactionRepository) {
        return new BLCreateOpeningTransactionImpl(transactionRepository);
    }

    @Bean
    public BLCreateTransaction blCreateTransaction(TransactionRepository transactionRepository, AccountRepository accountRepository, Map<String, TransactionStrategy> trnStrategies) {
        return new BLCreateTransactionImpl(transactionRepository, accountRepository, trnStrategies);
    }

    @Bean
    public BLFindAllTransaction blFindAllTransaction(TransactionRepository transactionRepository) {
        return new BLFindAllTransactionImpl(transactionRepository);
    }

    @Bean
    public BLFindAllTrnByAccountNumber blFindAllTrnByAccountNumber(TransactionRepository transactionRepository) {
        return new BLFindAllTrnByAccountNumberImpl(transactionRepository);
    }

    @Bean
    public BLFindByIdTransaction blFindByIdTransaction(TransactionRepository transactionRepository) {
        return new BLFindByIdTransactionImpl(transactionRepository);
    }

    @Bean
    public BLReportTransaction blReportTransaction(TransactionRepository transactionRepository) {
        return new BLReportTransactionImpl(transactionRepository);
    }

}
