package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.configuration;

import com.sofka.lab.accounts.app.accounts.application.port.in.AccountServicePort;
import com.sofka.lab.accounts.app.accounts.application.port.out.AccountPersistencePort;
import com.sofka.lab.accounts.app.accounts.application.service.AccountService;
import com.sofka.lab.accounts.app.accounts.application.service.factory.AccountFactory;
import com.sofka.lab.accounts.app.accounts.application.service.factory.impl.CheckingAccount;
import com.sofka.lab.accounts.app.accounts.application.service.factory.impl.SavingAccount;
import com.sofka.lab.accounts.app.transactions.application.port.out.TransactionPersistentPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.sofka.lab.accounts.app.transactions")
@ComponentScan("com.sofka.lab.accounts.app.accounts")
public class AccountConfiguration {


    @Bean
    public AccountFactory accountFactory() {
        return new AccountFactory(new CheckingAccount(), new SavingAccount());
    }

    @Bean
    public AccountServicePort accountServicePort(
            AccountPersistencePort dependency1,
            TransactionPersistentPort dependency2,
            AccountFactory dependency3
    ) {
        return new AccountService(dependency1, dependency2, dependency3);
    }




}
