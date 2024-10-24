package com.sofka.lab.accounts.app.models.dao;

import com.sofka.lab.accounts.app.models.dtos.MovimientoDto;
import com.sofka.lab.accounts.app.models.entity.Movimiento;
import com.sofka.lab.common.models.dtos.ReporteCuentasDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovimientoDao extends JpaRepository<Movimiento, Long> {


    @Query("SELECT NEW com.sofka.lab.accounts.app.models.dtos.MovimientoDto(m.id, m.fecha, m.tipo, m.valor, m.saldo) " +
            "from Movimiento m where m.cuenta.numero = ?1 order by m.fecha desc")
    List<MovimientoDto> findByNumeroCuenta(String numeroCuenta);


    @Query("SELECT NEW com.sofka.lab.common.models.dtos.ReporteCuentasDto(m.fecha, c.numero, c.tipo, c.saldoInicial, c.estado, m.valor, m.saldo) FROM Cuenta c " +
            "JOIN Movimiento m ON c.id = m.cuenta.id " +
            "WHERE c.clienteId = :clienteId " +
            "AND m.fecha BETWEEN :startDate AND :endDate " +
            "ORDER BY m.fecha DESC"
    )
    List<ReporteCuentasDto> report(
            @Param("clienteId") Long clienteId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

}
