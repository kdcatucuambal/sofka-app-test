package com.sofka.lab.common.exceptions.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorMessage {
    private String message;
    private List<ErrorDetail> details;
}
