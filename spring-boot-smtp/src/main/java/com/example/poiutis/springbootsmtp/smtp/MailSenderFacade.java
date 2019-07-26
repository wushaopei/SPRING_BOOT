package com.example.poiutis.springbootsmtp.smtp;

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
 * @ClassName MailSenderFacade
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/25 10:22
 * @Version 1.0
 */
@Component
public class MailSenderFacade {

    @Autowired
    private EmailConfig emailConfig;

    private final Logger logger = LoggerFactory.getLogger(MailSenderFacade.class);

    public void sendMailHtml(final String toEmail,final String subject,final String text){
        try{
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setUsername(emailConfig.getUsername());
            javaMailSender.setPassword(emailConfig.getPassword());
            javaMailSender.setHost(emailConfig.getHost());
            javaMailSender.setPort(25);
            javaMailSender.setProtocol("smtp");
            javaMailSender.setDefaultEncoding("UTF-8");
            Properties mailProperties = new Properties();
            mailProperties.put("mail.smtp.auth", true);
            mailProperties.put("mail.smtp.starttls.enable", true);
            javaMailSender.setJavaMailProperties(mailProperties);
            MimeMessage mimeMessage = getMimeMessage(toEmail,subject,text, javaMailSender);
            javaMailSender.send(mimeMessage);
            logger.info("发往 "+toEmail+" 邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发往 "+toEmail+" 邮件发送异常", e);
        }
    }


    private MimeMessage getMimeMessage(String toEmail,String subject,String text, JavaMailSenderImpl javaMailSender) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom(emailConfig.getSenderName());
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        return mimeMessage;
    }
}