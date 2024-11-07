package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.configuration;

import com.sofka.lab.accounts.app.accounts.application.port.out.AccountPersistencePort;
import com.sofka.lab.accounts.app.transactions.application.port.in.TransactionServicePort;
import com.sofka.lab.accounts.app.transactions.application.port.out.TransactionPersistentPort;
import com.sofka.lab.accounts.app.transactions.application.service.TransactionService;
import com.sofka.lab.accounts.app.transactions.application.service.strategy.TransactionStrategy;
import com.sofka.lab.accounts.app.transactions.application.service.strategy.impl.CreditStrategy;
import com.sofka.lab.accounts.app.transactions.application.service.strategy.impl.DebitStrategy;
import com.sofka.lab.accounts.app.transactions.application.service.util.TransactionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ComponentScan("com.sofka.lab.accounts.app.transactions")
@ComponentScan("com.sofka.lab.accounts.app.accounts")
public class TransactionConfiguration {


    @Bean
    public Map<String, TransactionStrategy> trnStrategies() {
        return Map.of(
                TransactionUtil.CREDIT_TYPE, new CreditStrategy(),
                TransactionUtil.DEBIT_TYPE, new DebitStrategy()
        );
    }

    @Bean
    public TransactionServicePort transactionServicePort(
            TransactionPersistentPort dependency1,
            AccountPersistencePort dependency2,
            Map<String, TransactionStrategy> dependency3) {
        return new TransactionService(dependency1, dependency2, dependency3);


    }

}
