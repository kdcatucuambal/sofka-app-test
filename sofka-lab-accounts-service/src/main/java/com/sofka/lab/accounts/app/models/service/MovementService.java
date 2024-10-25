package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.models.dtos.MovementDto;
import com.sofka.lab.accounts.app.models.entity.Movement;
import com.sofka.lab.common.dtos.AccountReportDto;

import java.util.Date;
import java.util.List;

public interface MovementService {


    Movement save(Movement movement);

    List<Movement> findAll();

    List<MovementDto> findByAccountNumber(String accountNumber);

    Movement findById(Long id);

    List<AccountReportDto> getAccountReportByCustomerIdentification(String identification, Date startDate, Date endDate);

}
