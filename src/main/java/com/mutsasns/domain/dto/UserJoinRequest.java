package com.mutsasns.domain.dto;

import com.mutsasns.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {
    private String userName;
    private String password;

    public User toEntity(String password) {
        return  User.builder()
                .userName(this.userName)
                .password(password)
                .build();
    }

}
