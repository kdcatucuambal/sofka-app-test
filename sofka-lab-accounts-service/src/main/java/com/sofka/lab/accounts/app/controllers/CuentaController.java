package com.sofka.lab.accounts.app.controllers;

import com.sofka.lab.accounts.app.models.dtos.CuentaDto;
import com.sofka.lab.accounts.app.models.service.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaDto save(@RequestBody CuentaDto cuentaDto) {
        return cuentaService.save(cuentaDto);
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
        cuentaDto.setId(id);
        return cuentaService.update(cuentaDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuentaService.delete(id);
    }

}
