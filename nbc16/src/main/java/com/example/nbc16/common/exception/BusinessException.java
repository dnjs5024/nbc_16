package com.example.nbc16.common.exception;

import lombok.Getter;

import com.example.nbc16.common.dto.ResponseCode;

public class BusinessException extends RuntimeException {

    @Getter
    private final ResponseCode responseCode;

    public BusinessException(ResponseCode responseCode) {
        super(responseCode.getDefaultMessage());
        this.responseCode = responseCode;
    }

    public BusinessException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

}
