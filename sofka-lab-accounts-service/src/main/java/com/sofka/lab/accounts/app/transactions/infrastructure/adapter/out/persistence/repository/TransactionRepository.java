package com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.repository;

import com.sofka.lab.accounts.app.transactions.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByAccount_NumberOrderByDateDesc(String accountNumber);

    @Query("select c from TransactionEntity c where c.account.number = ?1 order by c.date desc")
    List<TransactionEntity> findAllByAccountNumber(String accountNumber);

}
