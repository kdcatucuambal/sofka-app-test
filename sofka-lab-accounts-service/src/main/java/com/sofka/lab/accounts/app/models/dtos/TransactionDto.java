package com.sofka.lab.accounts.app.models.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TransactionDto {

    private Long id;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;
    private LocalDateTime date;

    public TransactionDto() {}

    public TransactionDto(BigDecimal amount, BigDecimal balance, Long id, LocalDateTime date, String type) {
        this.amount = amount;
        this.balance = balance;
        this.id = id;
        this.date = date;
        this.type = type;
    }

    public TransactionDto(Long id, LocalDateTime date, String type, BigDecimal amount, BigDecimal balance) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }





}
