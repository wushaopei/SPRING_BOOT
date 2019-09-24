package com.webcode.springboot.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @ClassName CredentialMatcher 密码校验规则的重写
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/24 10:07
 * @Version 1.0
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        String passsword = new String(token1.getPassword());
        String dbPassword = (String) info.getCredentials();
        return this.equals(passsword,dbPassword);
    }
}
