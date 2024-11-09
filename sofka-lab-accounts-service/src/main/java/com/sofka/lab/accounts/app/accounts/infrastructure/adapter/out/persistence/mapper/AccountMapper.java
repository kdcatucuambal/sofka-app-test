package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.mapper;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountModel;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountEntity toAccountEntity(AccountModel accountDomain);

    AccountModel toAccountModel(AccountEntity accountEntity);


}
