package com.sofka.lab.common.exceptions;

import com.sofka.lab.common.exceptions.models.ErrorDetail;
import com.sofka.lab.common.exceptions.models.ErrorMessage;
import com.sofka.lab.common.exceptions.models.interfaces.BusinessToHttpError;
import com.sofka.lab.common.exceptions.utils.Constants;
import com.sofka.lab.common.messages.ErrorMessageDto;
import com.sofka.lab.common.messages.ErrorMessageService;
import org.slf4j.Logger;
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

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final BusinessToHttpError businessToHttpError;
    private final Map<HttpStatus, String> httpCodeToReasonMapping;
    private final ErrorMessageService errorMessageService;


    public GlobalExceptionHandler(
            BusinessToHttpError businessToHttpError,
            Map<HttpStatus, String> httpCodeToReasonMapping,
            ErrorMessageService errorMessageService
    ) {
        this.businessToHttpError = businessToHttpError;
        this.httpCodeToReasonMapping = httpCodeToReasonMapping;
        this.errorMessageService = errorMessageService;
    }


    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorMessage> handleBusinessLogicException(BusinessLogicException ex) {
        logger.error("Business logic exception: ", ex);
        ErrorMessageDto errorMessageDto = this.errorMessageService.getMessage(ex.getBusinessCode());
        if (errorMessageDto == null) {
            return unhandledError(ex);
        }
        var httpStatus = HttpStatus.valueOf(errorMessageDto.getHttpErrorCode());
        var httpReason = httpCodeToReasonMapping.get(httpStatus);
        var detail = ErrorDetail.builder()
                .code(errorMessageDto.getCode().toString())
                .message(errorMessageDto.getMessageES())
                .build();
        var errorMessage = ErrorMessage.builder()
                .message(httpReason)
                .details(List.of(detail))
                .build();
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    private ResponseEntity<ErrorMessage> unhandledError(Exception ex) {
        logger.error("Unhandled exception: ", ex);
        return new ResponseEntity<>(ErrorMessage.builder()
                .message(Constants.GENERAL_MESSAGE_5xx)
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
