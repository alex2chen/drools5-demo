package com.xfboy.junit;

import org.drools.StatefulSession;
import org.drools.base.RuleNameEqualsAgendaFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DroolsSessionImpl
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
public class DroolsSessionImpl implements DroolsSession {
    private static final Logger LOG = LoggerFactory.getLogger(DroolsSessionImpl.class);
    private StatefulSession statefulSession;

    public DroolsSessionImpl(StatefulSession statefulSession) {
        this.statefulSession = statefulSession;
    }

    @Override
    public void fireAllRules() {
        this.statefulSession.fireAllRules();
    }

    @Override
    public void insert(Object object) {
        this.statefulSession.insert(object);
    }

    @Override
    public StatefulSession getStatefulSession() {
        return statefulSession;
    }
}
