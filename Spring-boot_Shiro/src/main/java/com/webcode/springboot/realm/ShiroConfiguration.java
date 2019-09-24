package com.webcode.springboot.realm;


import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
//

/**
 * @ClassName ShiroConfiguration
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/24 10:13
 * @Version 1.0
 */
@Configuration
public class ShiroConfiguration {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager){

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        bean.setSecurityManager(manager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        bean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        bean.setSuccessUrl("/index");
        // 未授权界面;
        bean.setUnauthorizedUrl("/unauthorized");

        // 拦截器.
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/index","authc");
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/loginUser","anon"); //登陸不用登陸認證
        filterChainDefinitionMap.put("/admin","roles[admin]"); //授权过程中认证角色为admin的用户才可以访问 /admin 接口
        filterChainDefinitionMap.put("/edit","perms[edit]");//具有edit 的pemission 才可以访问
        filterChainDefinitionMap.put("/**","user"); //其他接口要驗證是否登陸過用戶
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
    //配置核心安全事务管理器
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(authRealm);
        return securityManager;
    }
    //配置自定义的权限登录器
    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher")CredentialMatcher matcher){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher); //给出自定密码比较器
        return authRealm;       //返回自定Realm
    }
    //配置自定义的密码比较器
    @Bean("credentialMatcher")  //生成 密码校验规则的Bean
    public CredentialMatcher credentialMatcher(){
        return new CredentialMatcher(); //返回密码校验规则实例
    }
    /**
     * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持; Controller才能使用@RequiresPermissions
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

}
