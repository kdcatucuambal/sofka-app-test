package com.sofka.lab.common.exceptions.models;

import com.sofka.lab.common.exceptions.models.interfaces.BusinessToHttpError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BusinessToHttpErrorImpl implements BusinessToHttpError {
    private final Map<String, HttpStatus> businessToHttpErrorMapping;

    public BusinessToHttpErrorImpl(Map<String, HttpStatus> businessToHttpErrorMapping) {
        this.businessToHttpErrorMapping = businessToHttpErrorMapping;
    }

    public BusinessToHttpErrorImpl() {
        this.businessToHttpErrorMapping = new HashMap<>();
    }

    @Override
    public void addNewMapping(String code, HttpStatus httpStatus) {
        businessToHttpErrorMapping.put(code, httpStatus);
    }

    @Override
    public HttpStatus getHttpStatus(String code) {
        return businessToHttpErrorMapping.get(code);
    }
}
