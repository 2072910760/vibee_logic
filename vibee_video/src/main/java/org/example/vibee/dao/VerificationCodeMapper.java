package org.example.vibee.dao;

import org.example.vibee.entity.VerificationCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface VerificationCodeMapper {
    
    /**
     * 插入验证码
     */
    int insert(VerificationCode verificationCode);
    
    /**
     * 根据邮箱和类型查询最新的验证码
     */
    VerificationCode selectLatestByEmailAndType(@Param("email") String email, @Param("type") String type);
    
    /**
     * 根据邮箱、验证码和类型查询
     */
    VerificationCode selectByEmailAndCodeAndType(@Param("email") String email, @Param("code") String code, @Param("type") String type);
    
    /**
     * 更新验证码为已使用
     */
    int updateUsed(@Param("id") Integer id);
    
    /**
     * 删除过期的验证码
     */
    int deleteExpired(@Param("currentTime") Date currentTime);
} 