package com.sofka.lab.customers.app.models.entity.dtos;

import com.sofka.lab.customers.app.models.entity.CustomerEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(CustomerEntity customerEntity);
    CustomerEntity toEntity(CustomerDto customerDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CustomerDto dto, @MappingTarget CustomerEntity entity);

}
