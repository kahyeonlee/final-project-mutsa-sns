package com.mutsasns.domain.dto;

import com.mutsasns.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinResponse {
    private String userName;
    private String message;
}
