package com.example.demo.service.impl;

import com.example.demo.service.MyService;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {
    @Override
    public void test() {

        System.out.println("------------服务注入成功--------");
        System.out.println("------------操作中--------");
    }
}
