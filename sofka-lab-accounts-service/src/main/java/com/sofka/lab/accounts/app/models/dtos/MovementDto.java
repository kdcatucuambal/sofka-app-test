package com.sofka.lab.accounts.app.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class MovementDto {

    private Long id;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;

    private BigDecimal balance;

    public MovementDto() {
    }

    public MovementDto(BigDecimal amount, BigDecimal balance, Long id, LocalDateTime date, String type) {
        this.amount = amount;
        this.balance = balance;
        this.id = id;
        this.date = date;
        this.type = type;
    }

    public MovementDto(Long id, LocalDateTime date, String type, BigDecimal amount, BigDecimal balance) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }





}
