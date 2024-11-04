package com.sofka.lab.accounts.app.models.dtos;

import com.sofka.lab.accounts.app.models.entities.AccountEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDto {

    private Long id;
    private String number;
    private String type;
    private BigDecimal initBalance;
    private BigDecimal availableBalance;
    private Boolean status;
    private CustomerDto customerDto;

    public AccountDto() {}


    public AccountDto(BigDecimal availableBalance, CustomerDto customerId, Long id, String number,
                      BigDecimal initBalance, Boolean status, String type) {
        this.availableBalance = availableBalance;
        this.customerDto = customerId;
        this.id = id;
        this.number = number;
        this.initBalance = initBalance;
        this.status = status;
        this.type = type;
    }

//    public AccountEntity toEntity() {
//        AccountEntity account = new AccountEntity();
//        account.setId(id);
//        return account;
//    }


}
