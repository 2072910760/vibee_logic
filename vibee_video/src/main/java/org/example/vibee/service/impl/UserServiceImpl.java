package org.example.vibee.service.impl;

import org.example.vibee.dao.UserMapper;
import org.example.vibee.entity.User;
import org.example.vibee.entity.UserExample;
import org.example.vibee.service.UserService;
import org.example.vibee.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(User user) {
        // 1. 检查邮箱是否已被注册
        if (selectByEmail(user.getEmail()) != null) {
            return -1; // -1 表示邮箱已存在
        }
        // 2. 对密码进行MD5加密
        user.setPassword(MD5.getMD5(user.getPassword()));
        // 3. 设置默认状态
        user.setUserStatus((byte) 1); // 1-正常
        // 4. 插入用户数据
        userMapper.insertSelective(user);
        return user.getUserId();
    }

    @Override
    public User login(String email, String password) {
        User user = selectByEmail(email);
        if (user != null && user.getPassword().equals(MD5.getMD5(password))) {
            return user;
        }
        return null;
    }

    @Override
    public User selectByEmail(String email) {
        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(example);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User selectById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
} 