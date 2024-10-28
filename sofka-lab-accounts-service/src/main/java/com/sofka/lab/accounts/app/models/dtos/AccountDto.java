package com.sofka.lab.accounts.app.models.dtos;

import com.sofka.lab.accounts.app.models.entity.AccountEntity;
import com.sofka.lab.common.dtos.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDto {

    private Long id;
    private String number;
    private BigDecimal initBalance;
    private BigDecimal availableBalance;
    private String type;
    private Boolean status;
    private CustomerDto customer;

    public AccountDto() {}


    public AccountDto(BigDecimal availableBalance, CustomerDto customer, Long id, String number,
                      BigDecimal initBalance, Boolean status, String type) {
        this.availableBalance = availableBalance;
        this.customer = customer;
        this.id = id;
        this.number = number;
        this.initBalance = initBalance;
        this.status = status;
        this.type = type;
    }

    public AccountEntity toEntity() {
        AccountEntity account = new AccountEntity();
        account.setId(id);
        return account;
    }


}
