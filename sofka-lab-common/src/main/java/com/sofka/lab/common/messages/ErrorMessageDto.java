package com.sofka.lab.common.messages;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorMessageDto {

    private String messageES;
    private Integer httpErrorCode;
    private Integer code;

}
