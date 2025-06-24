package org.example.vibee.service;

/**
 * 邮件发送服务接口
 */
public interface EmailService {
    
    /**
     * 发送验证码邮件
     * @param to 收件人邮箱
     * @param code 验证码
     * @param type 验证码类型（register/reset_password）
     * @return 是否发送成功
     */
    boolean sendVerificationCode(String to, String code, String type);
    
    /**
     * 发送注册成功邮件
     * @param to 收件人邮箱
     * @param nickname 用户昵称
     * @return 是否发送成功
     */
    boolean sendRegistrationSuccess(String to, String nickname);
} 