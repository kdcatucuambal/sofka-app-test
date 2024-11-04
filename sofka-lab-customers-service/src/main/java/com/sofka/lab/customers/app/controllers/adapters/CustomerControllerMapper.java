package com.sofka.lab.customers.app.controllers.adapters;

import com.sofka.bank.objects.Customer;
import com.sofka.lab.customers.app.models.entity.CustomerEntity;
import com.sofka.lab.customers.app.models.entity.dtos.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerControllerMapper {

    @Mapping(target = "genre", expression = "java(toGenreEnum(customerDto))")
    Customer toCustomer(CustomerDto customerDto);

    CustomerDto toCustomerDto(Customer customer);

    @Mapping(target = "genre", expression = "java(fromGenreEnum(customer.getGenre()))")
    CustomerEntity toCustomerEntity(Customer customer);

    default Customer.GenreEnum toGenreEnum(CustomerDto customerDto) {
        return Customer.GenreEnum.fromValue(customerDto.getGenre());
    }

    default String fromGenreEnum(Customer.GenreEnum genreEnum) {
        return genreEnum.getValue();
    }

}
