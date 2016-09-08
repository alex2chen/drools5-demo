package com.xfboy.junit;

import com.xfboy.junit.annotations.DrlFiles;
import com.xfboy.junit.annotations.DrlSession;

import java.lang.reflect.Field;

/**
 * DroolsAnnotationProcessor
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
public class DroolsAnnotationProcessor {
    private Object testClass;
    public DroolsAnnotationProcessor(Object testClass){
        this.testClass=testClass;
    }

    /**
     * 获取注解上的文件
     * @return
     */
    public DrlFiles getDroolsFiles() {
        DrlFiles droolsFiles = testClass.getClass().getAnnotation(DrlFiles.class);
        if (droolsFiles == null) {
            throw new IllegalStateException("DroolsFiles annotation not set");
        }
        return droolsFiles;
    }

    /**
     * 实例化drools对象
     * @param droolsSession
     */
    public void setDroolsSession(DroolsSession droolsSession) {
        for (Field field : testClass.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(DrlSession.class)) {
                Object value = getValueToSet(droolsSession, field);

                try {
                    field.set(testClass, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Object getValueToSet(DroolsSession droolsSession, Field field) {
        Object toSet;
        if (field.getType().equals(DroolsSession.class)) {
            toSet = droolsSession;
        } else {
            toSet = droolsSession.getStatefulSession();
        }
        return toSet;
    }
}
