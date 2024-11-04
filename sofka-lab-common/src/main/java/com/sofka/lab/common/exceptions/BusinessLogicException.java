package com.sofka.lab.common.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessLogicException extends RuntimeException {

    private String businessCode;

    public BusinessLogicException(String message, String businessCode) {
        super(message);
        this.businessCode = businessCode;
    }

    public BusinessLogicException(String code) {
        super(code);
        this.businessCode = code;
    }

    public BusinessLogicException(Integer code) {
        super("Business error code: " + code.toString());
        this.businessCode = code.toString();
    }

}
