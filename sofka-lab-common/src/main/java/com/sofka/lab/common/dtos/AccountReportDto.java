package com.sofka.lab.common.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AccountReportDto {

    private String customer;
    private Date date;
    private String accountNumber;
    private String type;
    private BigDecimal initialBalance;
    private Boolean status;
    private BigDecimal movement;
    private BigDecimal availableBalance;

    public AccountReportDto(Date date, String accountNumber, String type, BigDecimal initialBalance, Boolean status, BigDecimal movement, BigDecimal availableBalance) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.type = type;
        this.initialBalance = initialBalance;
        this.status = status;
        this.movement = movement;
        this.availableBalance = availableBalance;
    }

}
