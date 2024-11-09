package com.sofka.lab.customers.app.infrastructure.adapters.out.rabbitmq.mapper;

import com.sofka.lab.common.domain.model.CustomerEvent;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {


    CustomerEvent toCustomerEvent(CustomerModel customerModel);

}
