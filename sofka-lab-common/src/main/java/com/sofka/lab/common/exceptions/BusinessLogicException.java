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

    public BusinessLogicException(String code){
        super(code);
        this.businessCode = code;
    }

}
