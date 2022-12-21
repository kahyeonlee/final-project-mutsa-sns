package com.mutsasns.service;

import com.mutsasns.domain.User;
import com.mutsasns.domain.dto.UserJoinRequest;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String join(UserJoinRequest dto) {

        //userName 중복체크
        userRepository.findByUserName(dto.getUserName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATION,"이미 존재하는 아이디입니다.");
                });

        //저장
        User user = User.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }
}
