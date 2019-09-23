package com.example.demo.service;


import com.example.demo.model.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/23 17:18
 * @Version 1.0
 */
public interface UserService {

    User findByUsername(String username);
}
