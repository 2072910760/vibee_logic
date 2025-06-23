package org.example.vibee.service;

import org.example.vibee.entity.User;

public interface UserService {

    /**
     * 用户注册
     * @param user 用户信息
     * @return 注册成功返回用户ID，否则返回-1
     */
    int register(User user);

    /**
     * 用户登录
     * @param email 邮箱
     * @param password 密码
     * @return 登录成功返回User对象，否则返回null
     */
    User login(String email, String password);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 返回User对象
     */
    User selectByEmail(String email);

    /**
     * 根据用户ID查询用户
     * @param userId 用户ID
     * @return 返回User对象
     */
    User selectById(Integer userId);
} 