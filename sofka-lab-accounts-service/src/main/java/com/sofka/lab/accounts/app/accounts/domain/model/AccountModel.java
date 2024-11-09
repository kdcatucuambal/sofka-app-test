package com.sofka.lab.accounts.app.accounts.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountModel {

    private Long id;
    private String number;
    private String type;
    private BigDecimal initBalance;
    private BigDecimal balance;
    private Boolean status;
    private Long customerId;

}
