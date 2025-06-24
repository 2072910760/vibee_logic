package org.example.vibee.service.impl;

import org.example.vibee.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public boolean sendVerificationCode(String to, String code, String type) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
            
        String subject = "";
        String content = "";
        
        if ("register".equals(type)) {
            subject = "Vibee视频平台 - 注册验证码";
            content = String.format(
                "您好！\n\n" +
                "感谢您注册Vibee视频平台。\n\n" +
                "您的验证码是：%s\n\n" +
                "验证码有效期为10分钟，请尽快完成注册。\n\n" +
                "如果这不是您的操作，请忽略此邮件。\n\n" +
                "祝您使用愉快！\n" +
                "Vibee视频平台团队", code
            );
        } else if ("reset_password".equals(type)) {
            subject = "Vibee视频平台 - 密码重置验证码";
            content = String.format(
                "您好！\n\n" +
                "您正在重置Vibee视频平台的密码。\n\n" +
                "您的验证码是：%s\n\n" +
                "验证码有效期为10分钟，请尽快完成密码重置。\n\n" +
                "如果这不是您的操作，请忽略此邮件。\n\n" +
                "祝您使用愉快！\n" +
                "Vibee视频平台团队", code
            );
        }
        
        message.setSubject(subject);
        message.setText(content);
        
        try {
            mailSender.send(message);
            log.info("成功发送验证码邮件到: {}", to);
            return true;
        } catch (MailException e) {
            log.error("发送邮件到 {} 失败: {}", to, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean sendRegistrationSuccess(String to, String nickname) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("Vibee视频平台 - 注册成功");
        message.setText(String.format(
            "您好 %s！\n\n" +
            "恭喜您成功注册Vibee视频平台！\n\n" +
            "现在您可以：\n" +
            "- 上传和分享您的视频\n" +
            "- 观看其他用户的精彩内容\n" +
            "- 与朋友互动交流\n\n" +
            "祝您使用愉快！\n" +
            "Vibee视频平台团队", nickname
        ));
        
        try {
            mailSender.send(message);
            log.info("成功发送注册成功邮件到: {}", to);
            return true;
        } catch (MailException e) {
            log.error("发送注册成功邮件到 {} 失败: {}", to, e.getMessage(), e);
            return false;
        }
    }
} 