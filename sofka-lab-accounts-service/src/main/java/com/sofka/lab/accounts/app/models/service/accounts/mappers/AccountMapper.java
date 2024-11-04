package com.sofka.lab.accounts.app.models.service.accounts.mappers;

import com.sofka.lab.accounts.app.models.dtos.AccountDto;
import com.sofka.lab.accounts.app.models.dtos.CustomerDto;
import com.sofka.lab.accounts.app.models.entities.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "customerId", expression = "java(getIdFromCustomerDto(accountDto.getCustomerDto()))")
    @Mapping(target = "balance", source = "availableBalance")
    AccountEntity toEntity(AccountDto accountDto);


    @Mapping(target = "customerDto", expression = "java(getCustomerDtoFromEntity(accountEntity))")
    @Mapping(target = "availableBalance", source = "balance")
    AccountDto toDto(AccountEntity accountEntity);

    default Long getIdFromCustomerDto(CustomerDto customerDto) {
        return customerDto.getId();
    }

    default CustomerDto getCustomerDtoFromEntity(AccountEntity accountEntity) {
        return CustomerDto.builder().id(accountEntity.getCustomerId()).build();
    }

}
