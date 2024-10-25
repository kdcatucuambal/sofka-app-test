package com.sofka.lab.accounts.app.models.service;

import com.sofka.lab.accounts.app.clients.ClienteRest;
import com.sofka.lab.accounts.app.models.dao.CuentaDao;
import com.sofka.lab.accounts.app.models.dao.MovimientoDao;
import com.sofka.lab.accounts.app.models.dtos.CuentaDto;
import com.sofka.lab.accounts.app.models.entity.Cuenta;
import com.sofka.lab.accounts.app.models.entity.Movimiento;
import com.sofka.lab.common.dtos.ClienteDto;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {


    private final CuentaDao cuentaDao;
    private final ClienteRest clienteRest;
    private final MovimientoDao movimientoDao;

    public CuentaServiceImpl(CuentaDao cuentaDao, ClienteRest clienteRest, MovimientoDao movimientoDao) {
        this.cuentaDao = cuentaDao;
        this.clienteRest = clienteRest;
        this.movimientoDao = movimientoDao;
    }


    @Override
    public CuentaDto save(CuentaDto cuentaDto) {
        ClienteDto clienteDto = null;
        Long id = cuentaDto.getCliente().getId();
        if (id != null) {
            clienteDto = clienteRest.findById(id);
        } else {
            clienteDto = clienteRest.findByIdentificacion(cuentaDto.getCliente().getIdentificacion());
        }

        if (clienteDto == null) {
            throw new BusinessLogicException("Cliente no encontrado", "100");
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(cuentaDto.getNumeroDeCuenta());
        cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta.setTipo(cuentaDto.getTipoDeCuenta());
        cuenta.setClienteId(clienteDto.getId());
        cuenta.setSaldo(cuenta.getSaldoInicial());
        cuenta = cuentaDao.save(cuenta);
        cuentaDto.setId(cuenta.getId());


        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setTipo("Apertura_Cuenta");
        movimiento.setSaldo(cuenta.getSaldoInicial());
        movimiento.setValor(cuenta.getSaldoInicial());
        movimiento.setFecha(new Date());
        movimientoDao.save(movimiento);

        return cuentaDto;
    }

    @Override
    public CuentaDto findById(Long id) {
        Cuenta cuenta = cuentaDao.findById(id).orElse(null);
        if (cuenta == null) {
            return null;
        }
        return cuenta.toDto();
    }

    @Override
    public CuentaDto findByNumero(String numeroCuenta) {
        Cuenta cuenta = cuentaDao.findByNumero(numeroCuenta);
        if (cuenta == null) {
            throw new BusinessLogicException("Cuenta no encontrada con el numero de cuenta proporcionado.", "102");
        }
        return cuenta.toDto();
    }

    @Override
    public CuentaDto update(CuentaDto cuenta) {
        Optional<Cuenta> cuentaDbOptional = cuentaDao.findById(cuenta.getId());

        if (cuentaDbOptional.isEmpty()) {
            throw new BusinessLogicException("Cuenta no encontrada", "101");
        }

        Cuenta cuentaDb = cuentaDbOptional.get();
        //cuentaDb.setSaldo(cuenta.getSaldoDisponible());
        cuentaDb.setNumero(cuenta.getNumeroDeCuenta());
        cuentaDb.setTipo(cuenta.getTipoDeCuenta());
        cuentaDb.setEstado(cuenta.getEstado());
        cuentaDao.save(cuentaDb);
        return cuenta;
    }


    @Override
    public List<CuentaDto> findAll() {
        return cuentaDao.findAll().stream().map(Cuenta::toDto).toList();
    }

    @Override
    public void updateSaldo(String numero, BigDecimal saldo) {
        Cuenta cuenta = this.cuentaDao.findByNumero(numero);
        cuenta.setSaldo(saldo);
        cuentaDao.save(cuenta);
    }


    @Override
    public void delete(Long id) {
        cuentaDao.deleteById(id);
    }
}
