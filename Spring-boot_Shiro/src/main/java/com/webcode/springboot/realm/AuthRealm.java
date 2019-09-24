package com.webcode.springboot.realm;


import com.webcode.springboot.entities.Permission;
import com.webcode.springboot.entities.Role;
import com.webcode.springboot.entities.User;
import com.webcode.springboot.services.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName AuthRealm
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/24 0:50
 * @Version 1.0
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 授权要先从 session 取出对象来
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();   //角色授权拦截
        Set<Role> roleSet = user.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)){
            for (Role role : roleSet) {
                roleNameList.add(role.getName()); //角色授权拦截
                Set<Permission> permissionSet = role.getPermissionSet();
                if (CollectionUtils.isNotEmpty(permissionSet)){
                    for (Permission permission : permissionSet) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        simpleAuthorizationInfo.addRoles(roleNameList);  //角色授权拦截
        return simpleAuthorizationInfo;
    }

    // 认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token; //将Token 降为 usernamePasswordToken
        String username = usernamePasswordToken.getUsername();    //去除其中的username
        User user = userService.findByUsername(username);       //根据username 查询到 user 信息
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName()); //密码直接取得数据库密码
    }
}
