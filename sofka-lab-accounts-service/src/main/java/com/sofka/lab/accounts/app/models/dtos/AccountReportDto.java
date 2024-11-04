package com.sofka.lab.accounts.app.models.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AccountReportDto {

    private Long id;
    private String customer;
    private LocalDateTime date;
    private String accountNumber;
    private String type;
    private BigDecimal initialBalance;
    private Boolean status;
    private BigDecimal movement;
    private BigDecimal availableBalance;

    public AccountReportDto(Long id, LocalDateTime date, String accountNumber, String type, BigDecimal
            initialBalance, Boolean status, BigDecimal movement, BigDecimal availableBalance) {
        this.id = id;
        this.date = date;
        this.accountNumber = accountNumber;
        this.type = type;
        this.initialBalance = initialBalance;
        this.status = status;
        this.movement = movement;
        this.availableBalance = availableBalance;
    }

}
