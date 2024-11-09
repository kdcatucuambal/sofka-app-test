package com.sofka.lab.customers.app.domain.model;

import lombok.AllArgsConstructor;;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public abstract class PersonModel {

    private Long id;
    private String name;
    private String genre;
    private Integer age;
    private String identification;
    private String phone;
    private String address;

}
