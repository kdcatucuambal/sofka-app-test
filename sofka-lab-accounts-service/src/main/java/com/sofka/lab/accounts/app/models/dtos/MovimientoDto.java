package com.sofka.lab.accounts.app.models.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class MovimientoDto {

    private Long id;
    private Date fecha;
    private String tipo;
    private BigDecimal valor;
    private BigDecimal saldo;


    public MovimientoDto(Long id, Date fecha, String tipo, BigDecimal valor, BigDecimal saldo) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    @Override
    public String toString() {
        return "MovimientoDto{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", saldo=" + saldo +
                '}';
    }
}
