package com.xfboy.login;

import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Test
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/9
 */
public class Test {
    public static void main(String []args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans-drools.xml");
        if (applicationContext != null) {
            LoginService loginService = (LoginService) applicationContext.getBean("loginService");
            StatefulKnowledgeSession ksession = (StatefulKnowledgeSession) applicationContext.getBean("ksession");
            Vip vip=new Vip();
//            vip.setName("alex");
//            vip.setPassword("1");
            loginService.checkLogin(ksession,vip);
            System.out.println("exit");
        }
    }
}
