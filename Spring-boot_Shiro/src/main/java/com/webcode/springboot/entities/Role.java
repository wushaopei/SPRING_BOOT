package com.webcode.springboot.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Role
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/23 17:10
 * @Version 1.0
 */
public class Role {

    private Integer rid;
    private String rname;
    private Set<Permission> permissionSet = new HashSet<>();
//    private Set<User> users = new HashSet<>();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getName() {
        return rname;
    }

    public void setName(String name) {
        this.rname = name;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", name='" + rname + '\'' +
                ", permissionSet=" + permissionSet +
//                ", users=" + users +
                '}';
    }
}
