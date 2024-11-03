package com.sofka.lab.common.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Deprecated
public class AccountReportDto {

    private String customer;
    private LocalDateTime date;
    private String accountNumber;
    private String type;
    private BigDecimal initialBalance;
    private Boolean status;
    private BigDecimal movement;
    private BigDecimal availableBalance;

    public AccountReportDto(LocalDateTime date, String accountNumber, String type, BigDecimal
            initialBalance, Boolean status, BigDecimal movement, BigDecimal availableBalance) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.type = type;
        this.initialBalance = initialBalance;
        this.status = status;
        this.movement = movement;
        this.availableBalance = availableBalance;
    }

}
