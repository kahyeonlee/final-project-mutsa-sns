package com.mutsasns.service;

import com.mutsasns.domain.entity.User;
import com.mutsasns.domain.dto.UserDto;
import com.mutsasns.domain.dto.UserJoinRequest;
import com.mutsasns.domain.dto.UserLoginRequest;
import com.mutsasns.domain.response.UserJoinResponse;
import com.mutsasns.domain.response.UserLoginResponse;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.repository.UserRepository;
import com.mutsasns.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 300l;

    public UserJoinResponse join(UserJoinRequest dto) {

        //userName 중복체크
        userRepository.findByUserName(dto.getUserName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATION);
                });

        //저장
        String securityPassword = encoder.encode(dto.getPassword());
        User savedUser = userRepository.save(dto.toEntity(securityPassword));

        UserDto userDto = UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .password(savedUser.getPassword())
                .role(savedUser.getRole())
                .build();

        return  new UserJoinResponse(userDto.getId(),userDto.getUserName());

    }

    public UserLoginResponse login(UserLoginRequest dto) {
        //username 없음
        User selectedUser = userRepository.findByUserName(dto.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        //password 틀림
        if (!encoder.matches(dto.getPassword(), selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        //토큰 발행
        String token = JwtTokenUtil.createToken(selectedUser.getUserName(), key, expireTimeMs);
        return new UserLoginResponse(token);
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));
    }

}
