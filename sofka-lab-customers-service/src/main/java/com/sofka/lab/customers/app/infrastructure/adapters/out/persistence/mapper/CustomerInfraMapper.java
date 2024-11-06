package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.mapper;

import com.sofka.lab.customers.app.domain.model.CustomerDomain;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.entity.CustomerEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerInfraMapper {

    CustomerEntity toCustomerEntity(CustomerDomain customerDomain);


    CustomerDomain toCustomerDomain(CustomerEntity customerEntity);

    @Mapping(target = "password", ignore = true)
    CustomerDomain toReadCustomerDomain(CustomerEntity customerEntity);


}
