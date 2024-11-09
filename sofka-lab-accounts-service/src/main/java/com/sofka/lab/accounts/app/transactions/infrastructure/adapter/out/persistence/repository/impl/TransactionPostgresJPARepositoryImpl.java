package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.repository.impl;

import com.sofka.lab.accounts.app.transactions.domain.model.TransactionModel;
import com.sofka.lab.accounts.app.transactions.domain.port.out.repository.TransactionRepository;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.mapper.TransactionMapper;
import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.repository.TransactionJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TransactionPostgresJPARepositoryImpl implements TransactionRepository {

    private final TransactionJPARepository transactionJPARepository;
    private final TransactionMapper mapper;

    @Override
    public List<TransactionModel> findAll() {
        return transactionJPARepository.findAll().stream().map(mapper::toTransactionModel).toList();
    }

    @Override
    public Optional<TransactionModel> findById(Long id) {
        return transactionJPARepository.findById(id).map(mapper::toTransactionModel);
    }

    @Override
    public TransactionModel save(TransactionModel transactionDomain) {
        return mapper.toTransactionModel(transactionJPARepository.save(mapper.toTransactionEntity(transactionDomain)));
    }

    @Override
    public List<TransactionModel> findAllByAccountNumber(String accountNumber) {
        return transactionJPARepository.findAllByAccountNumber(accountNumber).stream().map(mapper::toTransactionModel).toList();
    }
}
