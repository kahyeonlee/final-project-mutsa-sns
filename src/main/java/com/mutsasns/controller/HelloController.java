package com.mutsasns.controller;

import com.mutsasns.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;
    @GetMapping
    public String hello() {
        return "이가현";
    }

    @GetMapping("/{num}")
    public int sumOfDigit(@PathVariable Integer num){
        return helloService.sumMaker(num);
    }

}
