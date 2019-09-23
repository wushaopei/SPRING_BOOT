package com.example.poiutis.springbootsmtp.smtp;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName EmailConfig
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/25 10:24
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "email", ignoreUnknownFields = false)
//@PropertySource("classpath:/application.yml")
public class EmailConfig {
    private String host;
    private String username;
    private String password;
    private String senderName;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public String toString() {
        return "EmailConfig{" +
                "host='" + host + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}