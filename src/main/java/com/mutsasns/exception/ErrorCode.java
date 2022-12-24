package com.mutsasns.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATION(HttpStatus.CONFLICT,"ID가 중복됩니다."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND,"userName이 중복됩니다."),

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"password가 일치하지않습니다."),

    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"게시글을 찾을 수 없습니다."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED,"권한이 허용되지 않았습니다.");

    private HttpStatus httpStatus;
    private String message;
}
