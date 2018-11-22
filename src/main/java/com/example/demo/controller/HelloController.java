package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    public String Hello(){
        return "Hello World";
    }
}
