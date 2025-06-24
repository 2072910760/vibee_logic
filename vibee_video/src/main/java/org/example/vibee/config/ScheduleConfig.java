package org.example.vibee.config;

import org.example.vibee.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * 每小时清理一次过期的验证码
     */
    @Scheduled(fixedRate = 3600000) // 1小时 = 3600000毫秒
    public void cleanExpiredVerificationCodes() {
        verificationCodeService.cleanExpiredCodes();
    }
} 