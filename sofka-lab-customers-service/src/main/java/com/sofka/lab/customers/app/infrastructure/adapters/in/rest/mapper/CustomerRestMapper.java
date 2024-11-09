package com.sofka.lab.customers.app.infrastructure.adapters.in.rest.mapper;

import com.sofka.bank.objects.Customer;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRestMapper {

    @Mapping(target = "genre", expression = "java(fromGenreEnum(customer.getGenre()))")
    CustomerModel toCustomerDomain(Customer customer);

    @Mapping(target = "genre", expression = "java(toGenreEnum(customerDomain.getGenre()))")
    Customer toCustomer(CustomerModel customerDomain);


    default Customer.GenreEnum toGenreEnum(String genre) {
        if (genre == null) {
            return null;
        }
        return Customer.GenreEnum.fromValue(genre);
    }

    default String fromGenreEnum(Customer.GenreEnum genreEnum) {
        if (genreEnum == null) {
            return null;
        }
        return genreEnum.getValue();
    }

}
