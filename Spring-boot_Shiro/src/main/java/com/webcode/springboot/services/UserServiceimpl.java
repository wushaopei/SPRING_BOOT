package com.webcode.springboot.services;

import com.webcode.springboot.entities.User;
import com.webcode.springboot.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserServiceimpl
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/23 17:21
 * @Version 1.0
 */
@Service
@Transactional(readOnly=true)
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
