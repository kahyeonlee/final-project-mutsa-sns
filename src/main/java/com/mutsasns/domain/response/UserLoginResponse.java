package com.mutsasns.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserLoginResponse {
    private String jwt;

    public UserLoginResponse(String token) {
        this.jwt = token;
    }
}
