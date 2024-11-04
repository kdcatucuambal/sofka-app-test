package com.sofka.lab.accounts.app.models.dao;

import com.sofka.lab.accounts.app.models.dtos.AccountReportDto;
import com.sofka.lab.accounts.app.models.dtos.TransactionDto;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementDao extends JpaRepository<TransactionEntity, Long> {


    @Query("SELECT NEW com.sofka.lab.accounts.app.models.dtos.TransactionDto(m.id, m.date, m.type, m.amount, m.balance) " +
            "from TransactionEntity m where m.account.number = ?1 order by m.date desc")
    List<TransactionDto> findByAccountNumber(String number);


    @Query("SELECT NEW com.sofka.lab.accounts.app.models.dtos.AccountReportDto(m.id, m.date, c.number, m.type, c.initBalance, c.status," +
            " m.amount, m.balance) FROM AccountEntity c " +
            "JOIN TransactionEntity m ON c.id = m.account.id " +
            "WHERE c.customerId = :customerId " +
            "AND m.date BETWEEN :startDate AND :endDate " +
            "ORDER BY m.date DESC"
    )
    List<AccountReportDto> report(
            @Param("customerId") Long customerId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}
