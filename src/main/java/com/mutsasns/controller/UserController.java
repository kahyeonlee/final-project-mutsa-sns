package com.mutsasns.controller;

import com.mutsasns.domain.Response;
import com.mutsasns.domain.dto.UserDto;
import com.mutsasns.domain.dto.UserJoinRequest;
import com.mutsasns.domain.dto.UserJoinResponse;
import com.mutsasns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


//http://ec2-43-201-154-103.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/

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
}
