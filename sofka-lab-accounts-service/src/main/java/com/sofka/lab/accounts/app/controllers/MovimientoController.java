package com.sofka.lab.accounts.app.controllers;

import com.sofka.lab.accounts.app.exceptions.SofkaException;
import com.sofka.lab.accounts.app.models.dtos.MovimientoDto;
import com.sofka.lab.accounts.app.models.entity.Movimiento;
import com.sofka.lab.accounts.app.models.service.MovimientoService;
import com.sofka.lab.common.models.dtos.ReporteCuentasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movimiento save(@RequestBody Movimiento movimiento) {

        try {
            return movimientoService.save(movimiento);
        } catch (SofkaException e) {
            throw new ResponseStatusException(
                    e.getHttpStatus(),
                    e.getMessage()
            );
        }

    }

    @GetMapping
    public List<Movimiento> findAll() {
        return movimientoService.findAll();
    }

    @GetMapping("/{id}")
    public Movimiento findById(@PathVariable Long id) {
        return movimientoService.findById(id);
    }

    @GetMapping("/numero-cuenta/{numeroCuenta}")
    public List<MovimientoDto> findByNumeroCuenta(@PathVariable String numeroCuenta) {
        return movimientoService.findByNumeroCuenta(numeroCuenta);
    }




    @GetMapping("/reporte/{clienteId}/fechas")
    public List<ReporteCuentasDto> reporte(@PathVariable String clienteId,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        try {
            return movimientoService.getReporteCuentas(clienteId, startDate, endDate);
        } catch (SofkaException e) {
            throw new ResponseStatusException(
                    e.getHttpStatus(),
                    e.getMessage()
            );
        }

    }


}
