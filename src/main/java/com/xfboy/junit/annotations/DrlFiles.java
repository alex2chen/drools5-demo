package com.xfboy.junit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DroolsFiles
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DrlFiles {
    /**
     * DRL file names
     *
     * @return
     */
    String[] value();

    /**
     * DSL file names
     *
     * @return
     */
    String dsl() default "";

    /**
     * DRL files location relative to src/test/resources or src/main/resources
     *
     * @return
     */
    String location();
}
