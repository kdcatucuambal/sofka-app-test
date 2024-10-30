package com.sofka.lab.common.exceptions;

import com.sofka.lab.common.exceptions.models.ErrorDetail;
import com.sofka.lab.common.exceptions.models.ErrorMessage;
import com.sofka.lab.common.exceptions.models.interfaces.BusinessToHttpError;
import com.sofka.lab.common.exceptions.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.Objects;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final BusinessToHttpError businessToHttpError;
    private final Map<HttpStatus, String> httpCodeToReasonMapping;


    public GlobalExceptionHandler(
            BusinessToHttpError businessToHttpError,
            Map<HttpStatus, String> httpCodeToReasonMapping
    ) {
        this.businessToHttpError = businessToHttpError;
        this.httpCodeToReasonMapping = httpCodeToReasonMapping;
    }


    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorMessage> handleBusinessLogicException(BusinessLogicException ex) {

        HttpStatus httpStatus;
        if (ex.getBusinessCode().equals(Constants.CODE_5xx)) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            httpStatus = Objects.requireNonNullElse(
                    businessToHttpError.getHttpStatus(ex.getBusinessCode()), HttpStatus.CONFLICT);
        }
        var httpReason = httpCodeToReasonMapping.get(httpStatus);
        var detail = ErrorDetail.builder().code(ex.getBusinessCode()).message(ex.getMessage()).build();
        var errorMessage = ErrorMessage.builder()
                .message(httpReason)
                .details(List.of(detail))
                .build();
        return new ResponseEntity<>(errorMessage, httpStatus);
    }



}
