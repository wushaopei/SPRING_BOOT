package com.webcode.springboot.entities;

/**
 * @ClassName Permission
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/23 17:10
 * @Version 1.0
 */
public class Permission {

    private Integer pid;
    private String name;
    private String url;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
