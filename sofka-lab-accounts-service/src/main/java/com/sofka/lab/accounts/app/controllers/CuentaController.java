package com.sofka.lab.accounts.app.controllers;

import com.sofka.lab.accounts.app.exceptions.SofkaException;
import com.sofka.lab.accounts.app.models.dtos.CuentaDto;
import com.sofka.lab.accounts.app.models.entity.Cuenta;
import com.sofka.lab.accounts.app.models.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaDto save(@RequestBody CuentaDto cuentaDto) {
        try {
            return cuentaService.save(cuentaDto);
        } catch (SofkaException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }


    @GetMapping
    public List<CuentaDto> findAll() {
        return cuentaService.findAll();
    }

    @GetMapping("/{id}")
    public CuentaDto findById(@PathVariable Long id) {
        return cuentaService.findById(id);
    }

    @GetMapping("/numero-cuenta/{numero}")
    public CuentaDto findByNumero(@PathVariable String numero) {
        return cuentaService.findByNumero(numero);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaDto update(@RequestBody CuentaDto cuentaDto, @PathVariable Long id) {
        try {
            cuentaDto.setId(id);
            return cuentaService.update(cuentaDto);
        } catch (SofkaException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuentaService.delete(id);
    }

}
