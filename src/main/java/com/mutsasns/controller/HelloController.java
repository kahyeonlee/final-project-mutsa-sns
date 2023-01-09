package com.mutsasns.controller;

import com.mutsasns.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
@RequiredArgsConstructor
@Api(tags = "CI/CD test용")
public class HelloController {
    private final HelloService helloService;
    @GetMapping
    @Operation(summary = "회원가입", description = "sns을 이용하기 위한 회원 등록")
    public String hello() {
        return "이가현";
    }

    @GetMapping("/{num}")
    @Operation(summary = "sumOfDigit", description = "일의 자리수 더하기 ")
    public int sumOfDigit(@PathVariable Integer num){
        return helloService.sumMaker(num);
    }

}
