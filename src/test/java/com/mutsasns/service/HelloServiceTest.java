package com.mutsasns.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    HelloService helloService = new HelloService();

    @Test
    @DisplayName("num이 sum으로 잘 되는지 확인")
    void sumOfDigit_success() {
        assertEquals(15,helloService.sumMaker(12345));
    }
}