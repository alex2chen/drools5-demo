package com.xfboy.login;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.AgendaFilter;

/**
 * LoginServiceImpl
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/8
 */
public class LoginServiceImpl implements LoginService {
    public void checkLogin(StatefulKnowledgeSession session,Vip vip) {
        session.insert(vip);
        session.fireAllRules(new AgendaFilter() {
            public boolean accept(Activation activation) {
                System.out.println("rule "+activation.getRule().getName());
                return true;
            }
        });
        session.dispose();
    }

    public static boolean checkDB(String name, String password) {
        return name.trim().equals("alex") && password.trim().equals("123");
    }
}
