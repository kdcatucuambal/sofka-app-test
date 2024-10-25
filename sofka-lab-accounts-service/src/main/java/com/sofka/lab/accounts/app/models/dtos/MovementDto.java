package com.sofka.lab.accounts.app.models.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class MovementDto {

    private Long id;
    private Date date;
    private String type;
    private BigDecimal amount;

    private BigDecimal balance;

    public MovementDto() {
    }

    public MovementDto(BigDecimal amount, BigDecimal balance, Long id, Date date, String type) {
        this.amount = amount;
        this.balance = balance;
        this.id = id;
        this.date = date;
        this.type = type;
    }

    public MovementDto(Long id, Date date, String type, BigDecimal amount, BigDecimal balance) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }





}
