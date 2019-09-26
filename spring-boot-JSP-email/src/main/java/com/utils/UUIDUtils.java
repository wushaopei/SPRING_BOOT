package com.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtils  生成随机字符串工具类
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/8 13:52
 * @Version 1.0
 */

public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
