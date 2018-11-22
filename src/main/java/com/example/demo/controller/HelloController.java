package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String Hello(){
        return "Hello World 小袁修改源码";

    }
    @RequestMapping("/new")
    public String New(){
        return "小袁的新功能";

    }
}
