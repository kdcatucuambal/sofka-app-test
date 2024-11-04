package com.sofka.lab.accounts.app.models.service.movements;

import com.sofka.lab.accounts.app.models.dtos.AccountReportDto;
import com.sofka.lab.accounts.app.models.dtos.TransactionDto;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementService {

    TransactionEntity save(TransactionEntity movement);

    List<TransactionEntity> findAll();

    List<TransactionDto> findByAccountNumber(String accountNumber);

    TransactionEntity findById(Long id);

    List<AccountReportDto> getAccountReportByCustomerIdentification
            (String identification, LocalDateTime startDate, LocalDateTime endDate);

}
