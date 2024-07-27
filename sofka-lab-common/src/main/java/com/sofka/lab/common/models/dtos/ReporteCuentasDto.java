package com.sofka.lab.common.models.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class ReporteCuentasDto {

    private String cliente;

    private Date fecha;

    private String numeroDeCuenta;

    private String tipo;

    private BigDecimal saldoInicial;

    private Boolean estado;

    private BigDecimal movimiento;

    private BigDecimal saldoDisponible;


    public ReporteCuentasDto() {
    }

    public ReporteCuentasDto(Date fecha, String numeroDeCuenta, String tipo, BigDecimal saldoInicial, Boolean estado, BigDecimal movimiento, BigDecimal saldoDisponible) {
        this.fecha = fecha;
        this.numeroDeCuenta = numeroDeCuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.movimiento = movimiento;
        this.saldoDisponible = saldoDisponible;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public BigDecimal getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(BigDecimal movimiento) {
        this.movimiento = movimiento;
    }

    public BigDecimal getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(BigDecimal saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "cliente='" + cliente + '\'' +
                ", fecha=" + fecha +
                ", numeroDeCuenta='" + numeroDeCuenta + '\'' +
                ", tipo='" + tipo + '\'' +
                ", saldoInicial=" + saldoInicial +
                ", estado=" + estado +
                ", movimiento=" + movimiento +
                ", saldoDisponible=" + saldoDisponible +
                '}';
    }
}
