package com.sofka.lab.customers.app.domain.model;


import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CustomerDomain extends PersonDomain {

    private Boolean status;
    private String password;


}
