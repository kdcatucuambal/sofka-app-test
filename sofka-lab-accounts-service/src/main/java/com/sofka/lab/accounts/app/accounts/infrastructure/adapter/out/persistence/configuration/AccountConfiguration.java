package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.configuration;

import com.sofka.lab.accounts.app.accounts.application.port.in.AccountServicePort;
import com.sofka.lab.accounts.app.accounts.application.port.out.AccountPersistencePort;
import com.sofka.lab.accounts.app.accounts.application.port.out.CustomerRedisPort;
import com.sofka.lab.accounts.app.accounts.application.service.account.AccountService;
import com.sofka.lab.accounts.app.accounts.application.service.account.factory.AccountFactory;
import com.sofka.lab.accounts.app.accounts.application.service.account.factory.impl.CheckingAccount;
import com.sofka.lab.accounts.app.accounts.application.service.account.factory.impl.SavingAccount;
import com.sofka.lab.accounts.app.transactions.application.port.out.TransactionPersistentPort;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@ComponentScan("com.sofka.lab.accounts.app.transactions")
@ComponentScan("com.sofka.lab.accounts.app.accounts")
public class AccountConfiguration {


    @Bean
    public AccountFactory accountFactory() {
        return new AccountFactory(new CheckingAccount(), new SavingAccount());
    }

    @Bean
    @Transactional
    public AccountServicePort accountServicePort(
            AccountPersistencePort dependency1,
            TransactionPersistentPort dependency2,
            CustomerRedisPort dependency3,
            AccountFactory dependency4
    ) {
        return new AccountService(dependency1, dependency2, dependency3, dependency4);
    }



    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }



}
