package com.example.poiutis.springbootsmtp.smtp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName smtpController
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/26 10:55
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/webcode")
public class smtpController {


    @Autowired
    private MailSenderFacade mailSenderFacade;

    @RequestMapping(value = "/please/emailTest", method = RequestMethod.GET)
    public Object getEmailTest() {
        Map<String,Object> map = null;
        try{
            map = new HashMap<>();
            mailSenderFacade.sendMailHtml("18620307785@163.com", "来自邮箱测试接口邮件", "来自邮箱测试接口邮件");
            map.put("state","0");
            map.put("message", "發送成功");

        }catch (Exception e){
            map.put("state","1");
            map.put("message", "發送失敗");
            e.printStackTrace();
        }
        return map;
    }
}
