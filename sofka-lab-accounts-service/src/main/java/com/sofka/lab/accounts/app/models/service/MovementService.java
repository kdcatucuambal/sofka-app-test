package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.models.dtos.MovementDto;
import com.sofka.lab.accounts.app.models.entity.MovementEntity;
import com.sofka.lab.common.dtos.AccountReportDto;

import java.util.Date;
import java.util.List;

public interface MovementService {


    MovementEntity save(MovementEntity movement);

    List<MovementEntity> findAll();

    List<MovementDto> findByAccountNumber(String accountNumber);

    MovementEntity findById(Long id);

    List<AccountReportDto> getAccountReportByCustomerIdentification(String identification, Date startDate, Date endDate);

}
