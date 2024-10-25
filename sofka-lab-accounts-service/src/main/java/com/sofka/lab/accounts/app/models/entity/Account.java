package com.sofka.lab.accounts.app.models.entity;


import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.common.dtos.CustomerDto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id", nullable = false)
    private Long id;

    @Column(name = "acc_number", nullable = false, unique = true)
    private String number;

    @Column(name = "acc_type", nullable = false)
    private String type;

    @Column(name = "acc_init_balance", nullable = false)
    private BigDecimal initBalance;

    @Column(name = "acc_balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "acc_state", nullable = false)
    private Boolean status;

    @Column(name = "cli_id", nullable = false)
    private Long customerId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Movement> movements;



    public AccountDto toDto() {
        CustomerDto clienteDto = new CustomerDto();
        clienteDto.setId(customerId);
        return new AccountDto(balance, clienteDto, id, number, initBalance, status, type);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getInitBalance() {
        return initBalance;
    }

    public void setInitBalance(BigDecimal initBalance) {
        this.initBalance = initBalance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
