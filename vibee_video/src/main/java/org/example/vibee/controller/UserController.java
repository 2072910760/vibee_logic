package org.example.vibee.controller;

import org.example.vibee.entity.User;
import org.example.vibee.service.UserService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user 用户信息
     * @return a ResponseData
     */
    @PostMapping("register")
    public ResponseData register(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null || user.getNickname() == null) {
            return ResponseData.missParam("邮箱、密码或昵称");
        }
        int userId = userService.register(user);
        if (userId == -1) {
            return ResponseData.fail(40001, "邮箱已被注册");
        }
        return ResponseData.success(userId);
    }

    /**
     * 用户登录
     * @param user 包含邮箱和密码
     * @return a ResponseData
     */
    @PostMapping("login")
    public ResponseData login(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseData.missParam("邮箱或密码");
        }
        User loginUser = userService.login(user.getEmail(), user.getPassword());
        if (loginUser == null) {
            return ResponseData.fail(40002, "邮箱或密码错误");
        }
        // 登录成功，可以考虑返回Token等信息
        Map<String, Object> data = new HashMap<>();
        data.put("userId", loginUser.getUserId());
        data.put("nickname", loginUser.getNickname());
        data.put("avatarUrl", loginUser.getAvatarUrl());
        return ResponseData.success(data);
    }
} 