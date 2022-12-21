package com.mutsasns.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATION(HttpStatus.CONFLICT,"ID가 중복됩니다.");

    private HttpStatus httpStatus;
    private String message;
}
