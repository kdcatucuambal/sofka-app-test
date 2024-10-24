package com.sofka.lab.accounts.app.controllers;

import com.sofka.lab.accounts.app.models.dtos.CuentaDto;
import com.sofka.lab.accounts.app.models.service.CuentaService;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.common.exceptions.HttpCustomException;
import com.sofka.lab.common.exceptions.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    private final Map<String, String> environmentMapping;

    public CuentaController(CuentaService cuentaService, Map<String, String> environmentMapping) {
        this.cuentaService = cuentaService;
        this.environmentMapping = environmentMapping;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaDto save(@RequestBody CuentaDto cuentaDto) {
        try {
            return cuentaService.save(cuentaDto);
        } catch (BusinessLogicException e) {
            throw new HttpCustomException(e);
        } catch (Exception e) {
            throw new HttpCustomException(Constants.CODE_500);
        }
    }


    @GetMapping
    public List<CuentaDto> findAll() {
        System.out.println("Environment: " + environmentMapping.get("dev"));
        return cuentaService.findAll();
    }

    @GetMapping("/{id}")
    public CuentaDto findById(@PathVariable Long id) {
        return cuentaService.findById(id);
    }

    @GetMapping("/numero-cuenta/{numero}")
    public CuentaDto findByNumero(@PathVariable String numero) {
        try {
            return cuentaService.findByNumero(numero);
        } catch (BusinessLogicException e) {
            throw new HttpCustomException(e);
        } catch (Exception e) {
            throw new HttpCustomException(Constants.CODE_500);
        }
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaDto update(@RequestBody CuentaDto cuentaDto, @PathVariable Long id) {
        try {
            cuentaDto.setId(id);
            return cuentaService.update(cuentaDto);
        } catch (BusinessLogicException e) {
            throw new HttpCustomException(e);
        } catch (Exception e) {
            throw new HttpCustomException(Constants.CODE_500);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuentaService.delete(id);
    }

}
