package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.clients.ClienteRest;
import com.sofka.lab.accounts.app.exceptions.SofkaException;
import com.sofka.lab.accounts.app.models.dao.MovimientoDao;
import com.sofka.lab.accounts.app.models.dtos.CuentaDto;
import com.sofka.lab.accounts.app.models.dtos.MovimientoDto;
import com.sofka.lab.accounts.app.models.entity.Movimiento;
import com.sofka.lab.common.models.dtos.ClienteDto;
import com.sofka.lab.common.models.dtos.ReporteCuentasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoDao movimientoDao;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteRest clienteRest;


    @Override
    public Movimiento save(Movimiento movimiento) throws SofkaException {

        String numeroCuenta = movimiento.getCuenta().getNumero();

        CuentaDto cuentaDto = cuentaService.findByNumero(numeroCuenta);

        if (cuentaDto == null) {
            throw new SofkaException("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }

        BigDecimal saldo = cuentaDto.getSaldoDisponible().add(movimiento.getValor());
        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SofkaException("Saldo no disponible", HttpStatus.CONFLICT);
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
    public List<ReporteCuentasDto> getReporteCuentas(String clienteId, Date startDate, Date endDate) throws SofkaException {
        ClienteDto clienteDto = clienteRest.findByIdentificacion(clienteId);
        if (clienteDto == null) {
            throw new SofkaException("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }


        List<ReporteCuentasDto> reporteCuentas = movimientoDao.report(clienteDto.getId(), startDate, endDate);
        reporteCuentas.forEach(r -> r.setCliente(clienteDto.getNombre()));
        return reporteCuentas;
    }


}
