package com.mutsasns.domain.dto;

import com.mutsasns.domain.entity.User;
import com.mutsasns.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJoinRequest {
    private String userName;
    private String password;

    public User toEntity(String password) {
        return  User.builder()
                .userName(this.userName)
                .password(password)
                .role(UserRole.USER)
                .build();
    }

}
