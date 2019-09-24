package com.webcode.springboot.handlers;

import com.webcode.springboot.entities.User;
import com.webcode.springboot.mappers.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/24 10:50
 * @Version 1.0
 */
@Controller
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            subject.logout();
        }
        return "logout";
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin(HttpSession session){
        return "admin success";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @ResponseBody
    @RequestMapping("/edit")
    public String edit(){
        return "edit success";
    }


    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username")String username,
                            @RequestParam("password") String password,
                            HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User principal = (User) subject.getPrincipal();
            session.setAttribute("user",principal);
            return  "index";
        }catch (Exception e){
            return "login";
        }
    }

    @ResponseBody
    @RequestMapping("/get/user/list")
    public List<User> getUserList() {
        return userMapper.getAll();
    }

    @ResponseBody
    @RequestMapping("/get/emp/findUser")
    public User findUser() {
        return userMapper.findByUsername("admin");
    }

}
