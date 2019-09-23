package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName User
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/23 17:10
 * @Version 1.0
 */
public class User {

    private String username;
    private Integer uid;
    private String password;
    private Set<Role> roles = new HashSet<>();
    private Set<User> users = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", uid=" + uid +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", users=" + users +
                '}';
    }
}
