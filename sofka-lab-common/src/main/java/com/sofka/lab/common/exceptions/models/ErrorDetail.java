package com.sofka.lab.common.exceptions.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetail {
    private String code;
    private String message;
}
