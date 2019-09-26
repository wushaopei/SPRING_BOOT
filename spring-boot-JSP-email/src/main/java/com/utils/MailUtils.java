package com.utils;

import com.mysql.cj.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @ClassName MailUtils
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/8 14:49
 * @Version 1.0
 */
@Component
public class MailUtils {

    @Autowired
    private EmailConfig emailConfig;

    private final Logger logger = LoggerFactory.getLogger(MailUtils.class);
    /**
     * 发送邮件的方法
     * @Param to :给谁发邮件
     * @Param code : 邮件的激活码
     * @Param subject ： 主题
     * @Param text  : 内容
     *
     */
    public  void sendMail(String toEmail, String code,final String subject,final String text){
        try{
            //1、创建邮件对象
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            //2、发邮件人邮箱
            javaMailSender.setUsername(emailConfig.getUsername());
            //3、发邮件人邮箱密码（默认使用客户端的授权码）
            javaMailSender.setPassword(emailConfig.getPassword());
            //4、设置邮件服务器主机名  SMTP服务器地址
            javaMailSender.setHost(emailConfig.getHost());
            //5、SMTP服务器: 默认端口
            javaMailSender.setPort(25);
            //6、//发送邮件协议名称
            javaMailSender.setProtocol("smtp");
            //7、编码格式
            javaMailSender.setDefaultEncoding("UTF-8");

            //8、创建连接对象，连接到邮箱服务器
            Properties mailProperties = new Properties();
            //发送服务器需要身份验证,要采用指定用户名密码的方式去认证
            mailProperties.put("mail.smtp.auth", true);
            mailProperties.put("mail.smtp.starttls.enable", true);

            //9、添加连接对象到邮件对象中
            javaMailSender.setJavaMailProperties(mailProperties);

            int count = 1;
            //10、创建
            //可以发送几封邮件:可以这里 for循环多次
                MimeMessage mimeMessage = getMimeMessage(toEmail,subject,text, javaMailSender);
                //11、发送邮件
                javaMailSender.send(mimeMessage);
                logger.info("发送 第"+ count + "封邮件" );
                count ++;


            logger.info("发往 "+toEmail+" 邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发往 "+toEmail+" 邮件发送异常", e);
        }
    }


    //声明一个Message对象(代表一封邮件),从session中创建
    private MimeMessage getMimeMessage(String toEmail,String subject,String text, JavaMailSenderImpl javaMailSender) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        //发件人
        mimeMessageHelper.setFrom(emailConfig.getSenderName());
        //收件人  : 可以发送给多个收件人，该方法有一个重载的 数组形参
        mimeMessageHelper.setTo(toEmail);
//        mimeMessage.setContent();

        //邮件主题
        mimeMessageHelper.setSubject(subject);
        //邮件内容
        mimeMessageHelper.setText(text, true);

        return mimeMessage;
    }


}
