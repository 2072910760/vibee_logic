package org.example.vibee.service.impl;

import org.example.vibee.dao.VerificationCodeMapper;
import org.example.vibee.entity.VerificationCode;
import org.example.vibee.service.EmailService;
import org.example.vibee.service.VerificationCodeService;
import org.example.vibee.util.RandomCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;
    
    @Autowired
    private EmailService emailService;

    @Override
    public boolean sendVerificationCode(String email, String type) {
        try {
            // 生成6位数字验证码
            String code = RandomCodeUtils.getCheckCode();
            
            // 创建验证码记录
            VerificationCode verificationCode = new VerificationCode(email, code, type);
            
            // 保存到数据库
            verificationCodeMapper.insert(verificationCode);
            
            // 发送邮件
            boolean sendResult = emailService.sendVerificationCode(email, code, type);
            
            if (!sendResult) {
                // 如果邮件发送失败，可以考虑删除刚插入的记录
                return false;
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean verifyCode(String email, String code, String type) {
        try {
            // 查询验证码
            VerificationCode verificationCode = verificationCodeMapper.selectByEmailAndCodeAndType(email, code, type);
            
            if (verificationCode == null) {
                return false; // 验证码不存在
            }
            
            // 检查是否已使用
            if (verificationCode.getUsed()) {
                return false; // 验证码已使用
            }
            
            // 检查是否过期
            if (verificationCode.getExpireTime().before(new Date())) {
                return false; // 验证码已过期
            }
            
            // 标记为已使用
            verificationCodeMapper.updateUsed(verificationCode.getId());
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void cleanExpiredCodes() {
        try {
            verificationCodeMapper.deleteExpired(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 