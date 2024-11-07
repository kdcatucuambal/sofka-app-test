package com.sofka.lab.accounts.app.transactions.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDomain {

    private Long id;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;
    private LocalDateTime date;
    private Long accountId;
    private String accountNumber;

}
