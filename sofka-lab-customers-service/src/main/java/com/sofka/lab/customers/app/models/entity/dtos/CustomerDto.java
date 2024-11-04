package com.sofka.lab.customers.app.models.entity.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private String name;
    private String genre;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private Boolean status;

    public CustomerDto(Long id, String name, String genre, Integer age, String identification, String address,
                       String phone, Boolean status) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }
}
