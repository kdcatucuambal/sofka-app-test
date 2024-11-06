package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.mapper;

import com.sofka.lab.accounts.app.accounts.domain.model.AccountDomain;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountEntity toAccountEntity(AccountDomain accountDomain);

    AccountDomain toAccountDomain(AccountEntity accountEntity);


}
