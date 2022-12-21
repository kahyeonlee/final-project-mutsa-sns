package com.mutsasns.controller;

import com.mutsasns.domain.Response;
import com.mutsasns.domain.dto.UserJoinRequest;
import com.mutsasns.domain.dto.UserJoinResponse;
import com.mutsasns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//http://ec2-43-201-18-227.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserJoinRequest dto){
        userService.join(dto);
        UserJoinResponse userJoinResponse = new UserJoinResponse(dto.getUserName(), "회원가입에 성공하였습니다.");
        return ResponseEntity.ok().body(Response.success(userJoinResponse));
    }

}
