package com.webcode.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//扫描Mapper接口
@MapperScan("com.webcode.springboot.mappers")
@ComponentScan({"com.webcode.springboot"})
@SpringBootApplication
public class Bootstrap {
	
	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}

}
