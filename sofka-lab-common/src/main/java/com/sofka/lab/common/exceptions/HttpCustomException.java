package com.sofka.lab.common.exceptions;

public class HttpCustomException extends RuntimeException{

    private String blErrorCode;
    private String blErrorMessage;

    public HttpCustomException(String blErrorCode, String blErrorMessage) {
        super("Business Logic error code: " + blErrorCode + " Message: " + blErrorMessage);
        this.blErrorCode = blErrorCode;
        this.blErrorMessage = blErrorMessage;
    }

    public HttpCustomException(String messagForException, String blErrorCode, String blErrorMessage) {
        super(messagForException);
        this.blErrorCode = blErrorCode;
        this.blErrorMessage = blErrorMessage;
    }

    public HttpCustomException(String blErrorCode) {
        super("Business Logic error code: " + blErrorCode +
                " Message: Unknow error. Check the logs for more information");
        this.blErrorCode = blErrorCode;
        this.blErrorMessage = "Unknow error. Check the logs for more information";
    }

    public HttpCustomException(BusinessLogicException businessLogicException){
        super("Business Logic error code: " + businessLogicException.getBusinessCode() +
                " Message: " + businessLogicException.getMessage());
        this.blErrorCode = businessLogicException.getMessage();
        this.blErrorMessage = "Unknow error. Check the logs for more information";
    }

    public String getBlErrorCode() {
        return blErrorCode;
    }

    public void setBlErrorCode(String blErrorCode) {
        this.blErrorCode = blErrorCode;
    }

    public String getBlErrorMessage() {
        return blErrorMessage;
    }

    public void setBlErrorMessage(String blErrorMessage) {
        this.blErrorMessage = blErrorMessage;
    }
}
