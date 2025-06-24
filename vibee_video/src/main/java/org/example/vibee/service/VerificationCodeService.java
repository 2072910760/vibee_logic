package org.example.vibee.service;

/**
 * 验证码服务接口
 */
public interface VerificationCodeService {
    
    /**
     * 发送验证码
     * @param email 邮箱
     * @param type 验证码类型（register/reset_password）
     * @return 是否发送成功
     */
    boolean sendVerificationCode(String email, String type);
    
    /**
     * 验证验证码
     * @param email 邮箱
     * @param code 验证码
     * @param type 验证码类型
     * @return 是否验证成功
     */
    boolean verifyCode(String email, String code, String type);
    
    /**
     * 清理过期验证码
     */
    void cleanExpiredCodes();
} 