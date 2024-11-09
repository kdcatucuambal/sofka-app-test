package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.configuration;

import com.sofka.lab.accounts.app.accounts.application.business_logic.*;
import com.sofka.lab.accounts.app.accounts.application.business_logic.factory.AccountFactory;


import com.sofka.lab.accounts.app.accounts.application.business_logic.factory.impl.CheckingAccount;
import com.sofka.lab.accounts.app.accounts.application.business_logic.factory.impl.SavingAccount;
import com.sofka.lab.accounts.app.accounts.application.business_logic.impl.*;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.AccountRepository;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.CustomerQueryRepository;
import com.sofka.lab.accounts.app.accounts.domain.ports.out.SequenceRepository;
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
    public BLCreateAccount blCreateAccount(AccountRepository d1, SequenceRepository d2, CustomerQueryRepository d3, AccountFactory d4) {
        return new BLCreateAccountImpl(d1, d2, d3, d4);
    }

    @Bean
    public BLDeleteByIdAccount blDeleteByIdAccount(AccountRepository accountRepository) {
        return new BLDeleteByIdAccountImpl(accountRepository);
    }

    @Bean
    public BLFindAllAccount bLFindAllAccount(AccountRepository accountRepository) {
        return new BLFindAllAccountImpl(accountRepository);
    }

    @Bean
    public BLFindByIdAccount bLFindByIdAccount(AccountRepository accountRepository) {
        return new BLFindByIdAccountImpl(accountRepository);
    }

    @Bean
    public BLFindByNumberAccount bLFindByNumberAccount(AccountRepository accountRepository) {
        return new BLFindByNumberAccountImpl(accountRepository);
    }

    @Bean
    public BLUpdateAccount bLUpdateAccount(AccountRepository accountRepository) {
        return new BLUpdateAccountImpl(accountRepository);
    }



    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }



}
