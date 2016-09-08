package com.xfboy.junit;

import org.drools.StatefulSession;

/**
 * DroolsSession
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
public interface DroolsSession {
    void fireAllRules();

    void insert(Object object);

    StatefulSession getStatefulSession();
}
