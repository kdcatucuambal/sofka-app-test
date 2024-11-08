package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.redis.mapper;

import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.redis.hash.CustomerHash;
import com.sofka.lab.common.domain.model.CustomerEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRedisMapper {

    CustomerEvent toCustomerEvent(CustomerHash customerHash);
    CustomerHash toCustomerHash(CustomerEvent customerEvent);

}
