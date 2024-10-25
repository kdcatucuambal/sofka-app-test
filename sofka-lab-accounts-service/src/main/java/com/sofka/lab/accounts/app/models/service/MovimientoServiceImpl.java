package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.clients.ClienteRest;
import com.sofka.lab.accounts.app.models.dao.MovimientoDao;
import com.sofka.lab.accounts.app.models.dtos.CuentaDto;
import com.sofka.lab.accounts.app.models.dtos.MovimientoDto;
import com.sofka.lab.accounts.app.models.entity.Movimiento;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.common.dtos.ClienteDto;
import com.sofka.lab.common.dtos.ReporteCuentasDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private static final Logger logger = LoggerFactory.getLogger(MovimientoServiceImpl.class);

    private final MovimientoDao movimientoDao;
    private final CuentaService cuentaService;
    private final ClienteRest clienteRest;

    public MovimientoServiceImpl(MovimientoDao movimientoDao, CuentaService cuentaService, ClienteRest clienteRest) {
        this.movimientoDao = movimientoDao;
        this.cuentaService = cuentaService;
        this.clienteRest = clienteRest;
    }


    @Override
    public Movimiento save(Movimiento movimiento) {
        String numeroCuenta = movimiento.getCuenta().getNumero();
        CuentaDto cuentaDto = cuentaService.findByNumero(numeroCuenta);
        if (cuentaDto == null) {
            throw new BusinessLogicException("Cuenta no encontrada para la trnasacción.", "200");
        }

        BigDecimal saldo = cuentaDto.getSaldoDisponible().add(movimiento.getValor());
        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessLogicException("El saldo es insuficiente para la transacción.", "201");
        }
        //cuentaService.updateSaldo(numeroCuenta, saldo);
        movimiento.setValor(movimiento.getValor());
        movimiento.setSaldo(saldo);
        cuentaDto.setSaldoDisponible(saldo);
        movimiento.setCuenta(cuentaDto.toEntity());
        movimiento.setFecha(new Date());
        movimiento.setTipo(movimiento.getValor().compareTo(BigDecimal.ZERO) > 0 ? "Deposito" : "Retiro");
        cuentaService.updateSaldo(numeroCuenta, saldo);
        return movimientoDao.save(movimiento);
    }


    @Override
    public List<Movimiento> findAll() {
        return movimientoDao.findAll();
    }

    @Override
    public List<MovimientoDto> findByNumeroCuenta(String numeroCuenta) {
        return movimientoDao.findByNumeroCuenta(numeroCuenta);
    }

    @Override
    public Movimiento findById(Long id) {
        return movimientoDao.findById(id).orElse(null);
    }

    @Override
    public List<ReporteCuentasDto> getReporteCuentas(String clienteId, Date startDate, Date endDate)  {
        logger.info("startDates=>: {}", startDate);
        logger.info("endDates=>: {}", endDate);
        ClienteDto clienteDto = clienteRest.findByIdentificacion(clienteId);
        if (clienteDto == null) {
            throw new BusinessLogicException("Cliente no encontrado para generar el reporte.", "202");
        }
        List<ReporteCuentasDto> reporteCuentas = movimientoDao.report(clienteDto.getId(), startDate, endDate);
//        List<ReporteCuentasDto> reporteCuentas = movimientoDao.report(clienteDto.getId());
        reporteCuentas.forEach(r -> r.setCliente(clienteDto.getNombre()));
        return reporteCuentas;
    }


}
