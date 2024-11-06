package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence;

import com.sofka.bank.objects.Account;
import com.sofka.bank.objects.Customer;
import com.sofka.lab.accounts.app.transactions.application.port.out.TransactionPersistentPort;
import com.sofka.lab.accounts.app.transactions.domain.model.TransactionDomain;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.client.AccountFeignClient;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.mapper.TransactionMapper;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class TransactionPersistenceAdapter implements TransactionPersistentPort {


    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;


    @Override
    public List<TransactionDomain> findAll() {
        List<TransactionDomain> transactionDomains = new ArrayList<>();
        transactionRepository.findAll().forEach(transactionEntity -> {
            transactionDomains.add(transactionMapper.toTransactionDomain(transactionEntity));
        });
        return transactionDomains;
    }

    @Override
    public Optional<TransactionDomain> findById(Long id) {
        return transactionRepository.findById(id).map(transactionMapper::toTransactionDomain);
    }

    @Override
    public TransactionDomain save(TransactionDomain transactionDomain) {
        return transactionMapper.toTransactionDomain(transactionRepository.save(transactionMapper.toTransactionEntity(transactionDomain)));
    }

    @Override
    public List<TransactionDomain> findByIdAccountNumber(String accountNumber) {
        List<TransactionDomain> transactionDomains = new ArrayList<>();
        this.transactionRepository.findAllByAccountNumber(accountNumber)
                .forEach(transactionEntity -> transactionDomains.add(transactionMapper.toTransactionDomain(transactionEntity)));
        return transactionDomains;
    }

    @Override
    public void updateAccountBalance(String accountNumber, BigDecimal amount) {

    }

    @Override
    public Account findCustomerByAccountNumber(String accountNumber) {
        return null;
    }


}
