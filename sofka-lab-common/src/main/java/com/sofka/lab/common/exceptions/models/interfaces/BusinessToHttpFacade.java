package com.sofka.lab.common.exceptions.models.interfaces;

import org.springframework.http.HttpStatus;


public interface BusinessToHttpFacade {

    void addNewMapping(String code, HttpStatus httpStatus);
    HttpStatus getHttpStatus(String code);

}
