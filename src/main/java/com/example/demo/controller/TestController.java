package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/newFunction")
    public String test(){
        return "新的功能";
    }
}
