package com.entities;

import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/8 13:21
 * @Version 1.0
 */
@Data
public class User {

    private  Integer uid;
    private  String username;
    private  String password;
    private String nickname;
    private String email;
    private Integer state;
    private String code;

}
