package com.sofka.lab.customers.app.domain.model;


import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CustomerModel extends PersonModel {

    private Boolean status;
    private String password;


}
