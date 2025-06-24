package org.example.vibee.service;

import org.example.vibee.entity.User;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 用户注册
     * @param user 用户信息
     * @return 用户ID，-1表示邮箱已存在
     */
    int register(User user);

    /**
     * 用户登录
     * @param email 邮箱
     * @param password 密码
     * @return 用户信息，null表示登录失败
     */
    User login(String email, String password);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    User selectByEmail(String email);

    /**
     * 根据ID查询用户
     * @param userId 用户ID
     * @return 用户信息
     */
    User selectById(Integer userId);

    /**
     * 重置密码
     * @param email 邮箱
     * @param newPassword 新密码
     * @return 是否重置成功
     */
    boolean resetPassword(String email, String newPassword);
} 