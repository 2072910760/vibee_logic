package org.example.vibee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping(value = "/", produces = "text/plain;charset=UTF-8")
    public String test() {
        return "测试成功！后端服务正常运行";
    }
    
    @GetMapping(value = "/test", produces = "text/plain;charset=UTF-8")
    public String test2() {
        return "这是测试接口，服务正常";
    }
} 