package com.sofka.lab.accounts.app.models.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_movements")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id", nullable = false)
    private Long id;

    @Column(name = "mov_type", nullable = false)
    private String type;


    @Column(name = "mov_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "mov_balance", nullable = false)
    private BigDecimal balance;


    @Column(name = "mov_date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "acc_id", nullable = false)
    private AccountEntity account;


    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
