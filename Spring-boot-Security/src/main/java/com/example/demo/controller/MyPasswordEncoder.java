package com.example.demo.controller;


import com.example.demo.util.MD5Util;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * @ClassName MyPasswordEncoder
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/19 14:43
 * @Version 1.0
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    private final static String SALT = "wenmin";

    @Override
    public String encode(CharSequence charSequence) {

        return MD5Util.MD5Encode(charSequence.toString(),SALT);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        return MD5Util.isPasswordValid(encodedPassword,rawPassword.toString(),SALT);
    }
}
