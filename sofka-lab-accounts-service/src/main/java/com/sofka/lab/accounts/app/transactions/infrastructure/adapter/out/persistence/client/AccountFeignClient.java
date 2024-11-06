package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.client;

import com.sofka.bank.objects.Account;
import com.sofka.bank.objects.AccountGETByAccountNumberRs;
import com.sofka.bank.objects.AccountGETByCodeRs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient(name = "sofka-lab-accounts-service")
public interface AccountFeignClient {


    @PostMapping("/accounts/{accountNumber}/balance")
    void updateBalance(String accountNumber, BigDecimal amount);

    @GetMapping("/accounts/{accountNumber}")
    AccountGETByAccountNumberRs findByNumber(String accountNumber);

    @GetMapping("/accounts/{id}")
    AccountGETByCodeRs findById(Long id);

}
