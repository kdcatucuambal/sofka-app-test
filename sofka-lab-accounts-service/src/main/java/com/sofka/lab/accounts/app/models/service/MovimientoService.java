package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.models.dtos.MovimientoDto;
import com.sofka.lab.accounts.app.models.entity.Movimiento;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.common.dtos.ReporteCuentasDto;

import java.util.Date;
import java.util.List;

public interface MovimientoService {


    Movimiento save(Movimiento movimiento);

    List<Movimiento> findAll();

    List<MovimientoDto> findByNumeroCuenta(String numeroCuenta);

    Movimiento findById(Long id);

    List<ReporteCuentasDto> getReporteCuentas(String clienteId, Date startDate, Date endDate);

}
