package com.sofka.lab.accounts.app.controllers.adapters.mappers;

import com.sofka.bank.objects.Account;
import com.sofka.bank.objects.Customer;
import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.dtos.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountControllerMapper {

    @Mapping(target = "type", expression = "java(fromTypeEnum(account.getType()))")
    @Mapping(target = "availableBalance", source = "balance")
    @Mapping(target = "customerDto", expression = "java(getCustomerDtoFromAccount(account.getCustomer()))")
    AccountDto toAccountDto(Account account);


    @Mapping(target = "type", expression = "java(toTypeEnum(accountDto.getType()))")
    @Mapping(target = "balance", source = "availableBalance")
    @Mapping(target = "customer", expression = "java(getCustomerFromAccountDto(accountDto.getCustomerDto()))")
    Account toAccount(AccountDto accountDto);

    default String fromTypeEnum(Account.TypeEnum typeEnum) {
        return typeEnum.getValue();
    }

    default CustomerDto getCustomerDtoFromAccount(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId() != null ? customer.getId() : null)
                .identification(customer.getIdentification() != null ? customer.getIdentification() : null)
                .build();
    }

    default Customer getCustomerFromAccountDto(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId() != null ? customerDto.getId() : null)
                .identification(customerDto.getIdentification() != null ? customerDto.getIdentification() : null)
                .build();
    }


    default Account.TypeEnum toTypeEnum(String type) {
        return Account.TypeEnum.fromValue(type);
    }

}
