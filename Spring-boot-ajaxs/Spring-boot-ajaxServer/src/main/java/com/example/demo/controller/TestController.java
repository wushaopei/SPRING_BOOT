package com.example.demo.controller;

import com.example.demo.common.ResultBean;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/6 16:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get1")
    private ResultBean get1(){
        System.out.println("TestController.get1()");
        return new ResultBean("get1 OK");
    }

    @PostMapping("/postJson")
    public ResultBean postJson(@RequestBody User user){
        System.out.println("TestController.postJson");
        return new ResultBean("postJson"+ user.getName());
    }
}
