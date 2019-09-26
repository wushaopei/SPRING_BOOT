package com.handlers;

import com.entities.User;
import com.services.UserService;
import com.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/8 13:25
 * @Version 1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/get/userlist/user")
    public List<User> getAll() {
        return userService.getAll();
    }

    @ResponseBody
    @RequestMapping ("/get/getParam/user")
    public Object getParam(@RequestParam("username")String  username,
                           @RequestParam("password")String  password,
                           @RequestParam("nickname")String  nickname,
                           @RequestParam("email")String  email,
                           HttpServletRequest request
                           )  {
        Map<String,Object> map = null;
        try {
            request.setCharacterEncoding("UTF-8");

        if(username.equals("") && password.equals("") && nickname.equals("") && email.equals("")){

                return  "请不要留空";
            }
        //封装数据
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setState(0);// 0 ： 未激活 1： 已经激活

        //使用 UUID 随机生成激活码
        String code = UUIDUtils.getUUID()+ UUIDUtils.getUUID();
        user.setCode(code);

        map = new HashMap<>();
        //调用业务层处理数据
        userService.addUser(user);

        map.put("state","0");
        map.put("message", "發送成功");
        //页面跳转
        } catch (Exception e) {
//
            map.put("state","1");
            map.put("message", "發送失敗");
            e.printStackTrace();
            throw  new RuntimeException();
        }
        return map;
    }

    /*
    * 用户激活的 接口
    * */
    @ResponseBody
    @GetMapping("/regist_web/activateServlet")
    public Object activateServlet(@RequestParam("code")String  code,
                           HttpServletRequest request,HttpServletResponse response)  {
        Map<String,Object> map = null;
        try {
            map = new HashMap<>();
            User user = userService.findByCode(code);
            if(user != null){
                //已经查询到，修改用户的状态
                user.setState(1);//已经激活
                user.setCode(null);
                //激活后修改用户的激活码及状态
                userService.updateUser(user);
                map.put("state","0");
                map.put("message", "您的激活码已激活!请去登录");

            }else {
                //根据激活码没有查询到该用户
//
                map.put("state","0");
                map.put("message", "您的激活码有误!请重新激活");
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("state","1");
            map.put("message", "發送失敗");
            throw new RuntimeException();
        }

        return map;
    }


}
