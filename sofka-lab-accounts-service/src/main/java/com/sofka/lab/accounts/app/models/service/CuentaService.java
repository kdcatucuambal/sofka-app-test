package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.models.dtos.CuentaDto;

import java.math.BigDecimal;
import java.util.List;

public interface CuentaService {

    CuentaDto save(CuentaDto cuenta);

    CuentaDto findById(Long id);

    CuentaDto findByNumero(String numero);

    CuentaDto update(CuentaDto cuenta);

    List<CuentaDto> findAll();

    void updateSaldo(String numero, BigDecimal saldo);

    void delete(Long id);


}
