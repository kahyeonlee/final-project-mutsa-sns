package com.mutsasns.domain.response;

import com.mutsasns.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}