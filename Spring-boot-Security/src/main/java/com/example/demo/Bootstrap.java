package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Bootstrap {

	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}

	@RequestMapping("/")
	public String home (){
		return "hello spring boot";
	}

	@RequestMapping("/hello")
	public String hello (){
		return "hello spring boot";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostAuthorize("hasRole('')")
	@PreFilter("")
	@PostFilter("")
	@RequestMapping("/roleAuth")
	public String role1(){

		return "roleAuth";
	}
	//限制 id < 10 且 username与user.username匹配 并且 user中的username 为 abc
	@PreAuthorize("#id<10 or principal.username.equals(#username) and #user.username.equals('abc')")
	@PostAuthorize("returnObject%2==0")////方法调用后返回值能否被2取余为0进行过滤
	@RequestMapping("/test")
	public Integer test(Integer id,String username, User user){
		//....
		return id;
	}

	@PreFilter("filterObject%2==0")//方法调用前针对集合idList能否被2取余为0进行过滤
	@PostFilter("filterObject%4==0")//方法调用前针对集合idList能否被4取余为0进行过滤
	@RequestMapping("/test")
	public List<Integer> test2(List<Integer> idList){
		//....
		return idList;
	}
}
