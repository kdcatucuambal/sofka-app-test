package com.sofka.lab.accounts.app.models.entity;


import com.sofka.lab.accounts.app.models.dtos.CuentaDto;
import com.sofka.lab.common.models.dtos.ClienteDto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_accounts")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id", nullable = false)
    private Long id;

    @Column(name = "acc_number", nullable = false, unique = true)
    private String numero;

    @Column(name = "acc_type", nullable = false)
    private String tipo;

    @Column(name = "acc_init_balance", nullable = false)
    private BigDecimal saldoInicial;

    @Column(name = "acc_balance", nullable = false)
    private BigDecimal saldo;

    @Column(name = "acc_state", nullable = false)
    private Boolean estado;

    @Column(name = "cli_id", nullable = false)
    private Long clienteId;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Movimiento> movimientos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public CuentaDto toDto() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(clienteId);
        return new CuentaDto(id, numero, saldoInicial, saldo,
                tipo, estado, clienteDto);
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", saldoInicial=" + saldoInicial +
                ", estado=" + estado +
                ", clienteId=" + clienteId +
                ", saldo=" + saldo +
                '}';
    }
}
