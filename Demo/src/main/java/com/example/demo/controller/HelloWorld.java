package com.example.demo.controller;

import javax.xml.ws.RequestWrapper;

@Controller
public class HelloWorld {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello SpringBoot!";
    }
}
