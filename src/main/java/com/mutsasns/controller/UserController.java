package com.mutsasns.controller;

import com.mutsasns.domain.response.Response;
import com.mutsasns.domain.dto.*;
import com.mutsasns.domain.response.UserJoinResponse;
import com.mutsasns.domain.response.UserLoginResponse;
import com.mutsasns.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


//http://ec2-13-124-191-48.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/#/
//http://localhost:8080/swagger-ui/#/


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Api(tags = "1. 회원 인증, 인가")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "sns을 이용하기 위한 회원 등록")
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest dto){
        return Response.success(userService.join(dto));
    }

    @Operation(summary = "로그인", description = "로그인 시 token 발행")
    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest dto){
        return Response.success(userService.login(dto));
    }
}
