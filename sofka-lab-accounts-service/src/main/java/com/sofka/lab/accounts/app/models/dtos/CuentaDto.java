package com.sofka.lab.accounts.app.models.dtos;

import com.sofka.lab.accounts.app.models.entity.Cuenta;
import com.sofka.lab.common.dtos.ClienteDto;

import java.math.BigDecimal;


public class CuentaDto {

    private Long id;

    private String numeroDeCuenta;

    private BigDecimal saldoInicial;

    private BigDecimal saldoDisponible;

    private String tipoDeCuenta;

    private Boolean estado;

    private ClienteDto cliente;


    public CuentaDto() {
    }

    public CuentaDto(Long id, String numeroDeCuenta, BigDecimal saldoInicial, String tipoDeCuenta, Boolean estado, ClienteDto cliente) {
        this.id = id;
        this.numeroDeCuenta = numeroDeCuenta;
        this.saldoInicial = saldoInicial;
        this.tipoDeCuenta = tipoDeCuenta;
        this.estado = estado;
        this.cliente = cliente;
    }

    public CuentaDto(Long id, String numeroDeCuenta, BigDecimal saldoInicial, BigDecimal saldoDisponible, String tipoDeCuenta, Boolean estado, ClienteDto cliente) {
        this.id = id;
        this.numeroDeCuenta = numeroDeCuenta;
        this.saldoInicial = saldoInicial;
        this.saldoDisponible = saldoDisponible;
        this.tipoDeCuenta = tipoDeCuenta;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getTipoDeCuenta() {
        return tipoDeCuenta;
    }

    public void setTipoDeCuenta(String tipoDeCuenta) {
        this.tipoDeCuenta = tipoDeCuenta;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }


    public BigDecimal getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(BigDecimal saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public Cuenta toEntity() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(this.id);
        return cuenta;
    }
}
