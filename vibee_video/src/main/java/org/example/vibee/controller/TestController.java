package org.example.vibee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    
    @GetMapping("/")
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "测试成功！后端服务正常运行");
        return result;
    }
    
    @GetMapping("/test")
    public Map<String, Object> test2() {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "这是测试接口，服务正常");
        return result;
    }
} 