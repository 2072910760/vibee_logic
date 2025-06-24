package org.example.vibee.controller;

import org.example.vibee.entity.User;
import org.example.vibee.service.UserService;
import org.example.vibee.service.VerificationCodeService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * 发送验证码
     * @param email 邮箱
     * @param type 验证码类型（register/reset_password）
     * @return ResponseData
     */
    @PostMapping("sendVerificationCode")
    public ResponseData sendVerificationCode(@RequestParam String email, @RequestParam String type) {
        if (email == null || email.trim().isEmpty()) {
            return ResponseData.missParam("邮箱");
        }
        
        if (type == null || (!"register".equals(type) && !"reset_password".equals(type))) {
            return ResponseData.missParam("验证码类型");
        }
        
        boolean result = verificationCodeService.sendVerificationCode(email, type);
        if (result) {
            return ResponseData.success("验证码发送成功");
        } else {
            return ResponseData.fail(40003, "验证码发送失败，请重试");
        }
    }

    /**
     * 用户注册（带验证码验证）
     * @param user 用户信息
     * @param verificationCode 验证码
     * @return ResponseData
     */
    @PostMapping("register")
    public ResponseData register(@RequestBody User user, @RequestParam String verificationCode) {
        if (user.getEmail() == null || user.getPassword() == null || user.getNickname() == null) {
            return ResponseData.missParam("邮箱、密码或昵称");
        }
        
        if (verificationCode == null || verificationCode.trim().isEmpty()) {
            return ResponseData.missParam("验证码");
        }
        
        // 验证验证码
        boolean codeValid = verificationCodeService.verifyCode(user.getEmail(), verificationCode, "register");
        if (!codeValid) {
            return ResponseData.fail(40004, "验证码错误或已过期");
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
    public ResponseData login(@RequestBody User user, HttpSession session) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseData.missParam("邮箱或密码");
        }
        User loginUser = userService.login(user.getEmail(), user.getPassword());
        if (loginUser == null) {
            return ResponseData.fail(40002, "邮箱或密码错误");
        }
        
        // 登录成功，将用户信息存入Session
        session.setAttribute("currentUser", loginUser);
        
        // 登录成功，可以考虑返回Token等信息
        Map<String, Object> data = new HashMap<>();
        data.put("userId", loginUser.getUserId());
        data.put("nickname", loginUser.getNickname());
        data.put("avatarUrl", loginUser.getAvatarUrl());
        return ResponseData.success(data);
    }
    
    /**
     * 用户登出
     * @return a ResponseData
     */
    @PostMapping("logout")
    public ResponseData logout(HttpSession session) {
        session.invalidate(); // 销毁Session
        return ResponseData.success("登出成功");
    }

    /**
     * 重置密码
     * @param email 邮箱
     * @param verificationCode 验证码
     * @param newPassword 新密码
     * @return ResponseData
     */
    @PostMapping("resetPassword")
    public ResponseData resetPassword(@RequestParam String email, 
                                    @RequestParam String verificationCode, 
                                    @RequestParam String newPassword) {
        if (email == null || email.trim().isEmpty()) {
            return ResponseData.missParam("邮箱");
        }
        
        if (verificationCode == null || verificationCode.trim().isEmpty()) {
            return ResponseData.missParam("验证码");
        }
        
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseData.missParam("新密码");
        }
        
        // 验证验证码
        boolean codeValid = verificationCodeService.verifyCode(email, verificationCode, "reset_password");
        if (!codeValid) {
            return ResponseData.fail(40004, "验证码错误或已过期");
        }
        
        // 重置密码
        boolean result = userService.resetPassword(email, newPassword);
        if (result) {
            return ResponseData.success("密码重置成功");
        } else {
            return ResponseData.fail(40005, "密码重置失败，用户不存在");
        }
    }
} 