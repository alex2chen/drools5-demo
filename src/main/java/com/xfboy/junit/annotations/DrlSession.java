package com.xfboy.junit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DrlSession
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DrlSession {
}
