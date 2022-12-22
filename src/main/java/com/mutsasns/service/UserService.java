package com.mutsasns.service;

import com.mutsasns.configuration.EncoderConfig;
import com.mutsasns.domain.User;
import com.mutsasns.domain.dto.UserDto;
import com.mutsasns.domain.dto.UserJoinRequest;
import com.mutsasns.domain.dto.UserLoginRequest;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.repository.UserRepository;
import com.mutsasns.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs= 1000*60*60l;

    public UserDto join(UserJoinRequest dto) {

        //userName 중복체크
        userRepository.findByUserName(dto.getUserName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATION,"이미 존재하는 아이디입니다.");
                });

        //저장
        String securityPassword = encoder.encode(dto.getPassword());
        User savedUser = userRepository.save(dto.toEntity(securityPassword));

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .password(savedUser.getPassword())
                .userRole(savedUser.getRole())
                .build();
    }

    public String login(UserLoginRequest dto){
        //username 없음
        User selectedUser = userRepository.findByUserName(dto.getUserName())
                .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND,dto.getUserName()+"이 없습니다."));

        //password 틀림
        if(!encoder.matches(dto.getPassword(),selectedUser.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력하셨습니다.");
        }

        //토큰 발행
        String token = JwtTokenUtil.createToken(selectedUser.getUserName(), key, expireTimeMs);
        return token;
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, ""));
    }

}
