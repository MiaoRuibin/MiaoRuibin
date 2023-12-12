package com.itcodai.course16.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;

public class Test {
    SimpleAccountRealm realm = new SimpleAccountRealm();
    @Before
    public void before() {
        realm.addAccount("admin", "admin", "admin");
        realm.addAccount("user", "user", "user");
    }
    @org.junit.Test
    public void tset(){
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(realm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin");
        subject.login(usernamePasswordToken);
        subject.checkRole("user");
        System.out.println("isAuthenticated"+subject.isAuthenticated());
        subject.logout();
        System.out.println("isAuthenticated"+subject.isAuthenticated());
    }
}
