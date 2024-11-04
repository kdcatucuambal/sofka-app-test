package com.sofka.lab.accounts.app.models.service.movements.mappers;

import com.sofka.lab.accounts.app.models.dtos.TransactionDto;
import com.sofka.lab.accounts.app.models.entities.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionEntity toEntity(TransactionDto transactionDto);

    TransactionDto toDto(TransactionEntity transactionEntity);


}
