package com.mutsasns.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public int sumMaker(Integer id) {
        int sum = 0;
        while (id > 0) {
            sum += id % 10;
            id = id / 10;
        }
        return sum;
    }
}
