package org.example.vibee.entity;

import java.util.Date;

/**
 * 验证码实体类
 */
public class VerificationCode {
    private Integer id;
    private String email;
    private String code;
    private String type; // register, reset_password
    private Date createTime;
    private Date expireTime;
    private Boolean used;

    public VerificationCode() {}

    public VerificationCode(String email, String code, String type) {
        this.email = email;
        this.code = code;
        this.type = type;
        this.createTime = new Date();
        this.expireTime = new Date(System.currentTimeMillis() + 10 * 60 * 1000); // 10分钟过期
        this.used = false;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
} 