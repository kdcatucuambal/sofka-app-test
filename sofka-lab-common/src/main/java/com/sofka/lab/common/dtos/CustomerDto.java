package com.sofka.lab.common.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class CustomerDto {
    private Long id;
    private String identification;
    private String name;
    private String genre;
    private Integer age;
    private String address;
    private String phone;
    private Boolean status;

    public CustomerDto(Long id, String identification, String name,
                       String genre, Integer age, String address, String phone, Boolean status) {
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.genre = genre;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

}
