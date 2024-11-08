package com.sofka.lab.common.domain.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerEvent {
    private Long id;
    private String name;
    private String identification;
    private Boolean status;
}
