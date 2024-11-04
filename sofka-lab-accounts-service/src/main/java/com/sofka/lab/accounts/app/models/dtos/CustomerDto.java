package com.sofka.lab.accounts.app.models.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerDto {

    private Long id;
    private String identification;

}
