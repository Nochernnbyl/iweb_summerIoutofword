package com.iweb.util.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * @author ASUS
 * @Date 2023/7/19 18:53
 * @Version 1.8
 */
public @interface Excel {
    String name () default "";
}
