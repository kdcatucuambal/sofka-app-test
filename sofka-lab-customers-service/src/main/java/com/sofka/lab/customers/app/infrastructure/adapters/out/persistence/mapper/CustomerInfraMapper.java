package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.mapper;

import com.sofka.lab.common.domain.model.CustomerEvent;
import com.sofka.lab.customers.app.domain.model.CustomerDomain;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.entity.CustomerEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface CustomerInfraMapper {

    CustomerEntity toCustomerEntity(CustomerDomain customerDomain);


    CustomerDomain toCustomerDomain(CustomerEntity customerEntity);

    @Mapping(target = "password", ignore = true)
    CustomerDomain toReadCustomerDomain(CustomerEntity customerEntity);

    CustomerEvent toCustomerEventDomain(CustomerEntity customerDomain);


}
