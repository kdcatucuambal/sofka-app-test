package com.sofka.lab.accounts.app.models.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_movements")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id", nullable = false)
    private Long id;

    @Column(name = "mov_type", nullable = false)
    private String tipo;


    @Column(name = "mov_amount", nullable = false)
    private BigDecimal valor;

    @Column(name = "mov_balance", nullable = false)
    private BigDecimal saldo;


    @Column(name = "mov_date", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "acc_id", nullable = false)
    private Cuenta cuenta;


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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }


    public BigDecimal getValor() {


        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
