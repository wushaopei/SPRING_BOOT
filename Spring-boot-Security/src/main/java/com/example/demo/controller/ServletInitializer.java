package com.example.demo.controller;

import com.example.demo.Bootstrap;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @ClassName ServletInitializer 告诉程序，项目启动时从 Bootstrap 开始
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/19 10:30
 * @Version 1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Bootstrap.class);
    }
}
