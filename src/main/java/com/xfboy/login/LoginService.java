package com.xfboy.login;

import org.drools.runtime.StatefulKnowledgeSession;

/**
 * LoginService
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/8
 */
public interface LoginService {
    void checkLogin(StatefulKnowledgeSession session,Vip vip);
}
