package com.example.demo.controller;

/**
 * @ClassName User
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/7 18:14
 * @Version 1.0
 */
public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
