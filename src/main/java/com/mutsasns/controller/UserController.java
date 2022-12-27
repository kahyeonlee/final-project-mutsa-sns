package com.mutsasns.controller;

import com.mutsasns.domain.response.Response;
import com.mutsasns.domain.dto.*;
import com.mutsasns.domain.response.UserJoinResponse;
import com.mutsasns.domain.response.UserLoginResponse;
import com.mutsasns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


//http://ec2-3-34-99-55.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/#/


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest dto){
        UserDto userDto = userService.join(dto);
        UserJoinResponse response = new UserJoinResponse(userDto.getId(),userDto.getUserName());
        return Response.success(response);
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest dto){
        String token = userService.login(dto);
        UserLoginResponse response = new UserLoginResponse(token);
        return Response.success(response);
    }
}
