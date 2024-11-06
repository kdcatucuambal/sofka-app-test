package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class AccountEntity {

    @Id
    @Column(name = "acc_id", nullable = false)
    private Long id;

    @Column(name = "acc_number", nullable = false, unique = true, length = 15)
    private String number;

    @Column(name = "acc_type", nullable = false, length = 3)
    private String type;

    @Column(name = "acc_init_balance", nullable = false)
    private BigDecimal initBalance;

    @Column(name = "acc_balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "acc_state", nullable = false)
    private Boolean status;

    @Column(name = "cli_id", nullable = false)
    private Long customerId;



}
