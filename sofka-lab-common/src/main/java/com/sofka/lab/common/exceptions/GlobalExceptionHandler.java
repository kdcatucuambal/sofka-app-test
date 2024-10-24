package com.sofka.lab.common.exceptions;

import com.sofka.lab.common.exceptions.models.ErrorDetail;
import com.sofka.lab.common.exceptions.models.ErrorMessage;
import com.sofka.lab.common.exceptions.models.interfaces.BusinessToHttpFacade;
import com.sofka.lab.common.exceptions.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final BusinessToHttpFacade businessToHttpFacade;
    private final Map<HttpStatus, String> httpCodeToReasonMapping;


    public GlobalExceptionHandler(
            BusinessToHttpFacade businessToHttpFacade,
            Map<HttpStatus, String> httpCodeToReasonMapping
    ) {
        this.businessToHttpFacade = businessToHttpFacade;
        this.httpCodeToReasonMapping = httpCodeToReasonMapping;
    }

    @ExceptionHandler(HttpCustomException.class)
    public ResponseEntity<ErrorMessage> handleCustomError(HttpCustomException ex) {

        HttpStatus httpStatus;
        if (ex.getBlErrorCode().equals(Constants.CODE_500)) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            httpStatus = Objects.requireNonNullElse(businessToHttpFacade.getHttpStatus(ex.getBlErrorCode()), HttpStatus.CONFLICT);
        }
        var httpReason = httpCodeToReasonMapping.get(httpStatus);
        System.out.println("HTTP_REASON: " + httpReason);
        var detail = ErrorDetail.builder().code(ex.getBlErrorCode()).message(ex.getBlErrorMessage()).build();
        var errorMessage = ErrorMessage.builder()
                .message(httpReason)
                .details(List.of(detail))
                .build();
        return new ResponseEntity<>(errorMessage, httpStatus);
    }


}
