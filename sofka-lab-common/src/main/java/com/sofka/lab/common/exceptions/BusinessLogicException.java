package com.sofka.lab.common.exceptions;

public class BusinessLogicException extends Exception {

    private String businessCode;

    public BusinessLogicException(String message, String businessCode) {
        super(message);
        this.businessCode = businessCode;
    }

    public BusinessLogicException(String code){
        super(code);
        this.businessCode = code;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
