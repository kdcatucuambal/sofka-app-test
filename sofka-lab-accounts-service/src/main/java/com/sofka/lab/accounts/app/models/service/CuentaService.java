package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.models.dtos.CuentaDto;
import com.sofka.lab.common.exceptions.BusinessLogicException;

import java.math.BigDecimal;
import java.util.List;

public interface CuentaService {

    CuentaDto save(CuentaDto cuenta) throws BusinessLogicException;

    CuentaDto findById(Long id);

    CuentaDto findByNumero(String numero) throws BusinessLogicException;

    CuentaDto update(CuentaDto cuenta) throws BusinessLogicException;

    List<CuentaDto> findAll();

    void updateSaldo(String numero, BigDecimal saldo);

    void delete(Long id);


}
