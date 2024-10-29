package com.sofka.lab.accounts.app.models.dao;

import com.sofka.lab.accounts.app.models.dtos.MovementDto;
import com.sofka.lab.accounts.app.models.entity.MovementEntity;
import com.sofka.lab.common.dtos.AccountReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface MovementDao extends JpaRepository<MovementEntity, Long> {


    @Query("SELECT NEW com.sofka.lab.accounts.app.models.dtos.MovementDto(m.id, m.date, m.type, m.amount, m.balance) " +
            "from MovementEntity m where m.account.number = ?1 order by m.date desc")
    List<MovementDto> findByAccountNumber(String number);


    @Query("SELECT NEW com.sofka.lab.common.dtos.AccountReportDto(m.date, c.number, c.type, c.initBalance, c.status," +
            " m.amount, m.balance) FROM AccountEntity c " +
            "JOIN MovementEntity m ON c.id = m.account.id " +
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
