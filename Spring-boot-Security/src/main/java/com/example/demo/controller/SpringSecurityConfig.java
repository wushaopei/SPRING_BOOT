package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @ClassName SpringSecurityConfig
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/19 10:48
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyUserService myUserService;
    /*
    *  告诉程序，系统中有个用户 用户名为 admin ,密码为 admin  角色为 ADMIN
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //可以设置内存指定的登录的账号密码,指定角色
        //不加.passwordEncoder(new MyPasswordEncoder())
        //就不是以明文的方式进行匹配，会报错
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("demo").password("demo").roles("DEMO");
        //.passwordEncoder(new MyPasswordEncoder())。
        //这样，页面提交时候，密码以明文的方式进行匹配。
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("wsp").password("wsp").roles("ADMIN");
        /*
        *  目前SpringSecurity 可以支持指定多個人的登陸
        * */
        auth.userDetailsService(myUserService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()  //安全请求策略
                .antMatchers("/").permitAll() //可放行请求配置
                .anyRequest().authenticated()    //其他请求进行拦截
                .and()
                .logout().permitAll()  // 注销任意访问
                .and()
                .formLogin();
        http.cors().disable();
    }

    /*
    *  前端拦截
    * */
    @Override
    public void configure(WebSecurity web){
        // 忽视 js 、css 、 images 后缀访问
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }

}
