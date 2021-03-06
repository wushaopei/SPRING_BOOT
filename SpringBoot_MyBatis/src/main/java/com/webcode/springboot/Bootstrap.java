package com.webcode.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描Mapper接口
@MapperScan("com.webcode.springboot.mappers")
@SpringBootApplication
public class Bootstrap {
	
	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}

}
