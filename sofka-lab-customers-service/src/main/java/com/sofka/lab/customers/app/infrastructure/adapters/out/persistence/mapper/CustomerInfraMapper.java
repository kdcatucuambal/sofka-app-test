package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.mapper;

import com.sofka.lab.common.domain.model.CustomerEvent;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.entity.CustomerEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface CustomerInfraMapper {

    CustomerEntity toCustomerEntity(CustomerModel customerDomain);


    CustomerModel toCustomerModel(CustomerEntity customerEntity);

    @Mapping(target = "password", ignore = true)
    CustomerModel toReadCustomerModel(CustomerEntity customerEntity);

    CustomerEvent toCustomerEventDomain(CustomerEntity customerDomain);


}
