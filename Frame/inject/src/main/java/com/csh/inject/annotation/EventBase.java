package com.csh.inject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ChenShuHui
 * @className EventBase
 * @email chenshuhui@corp.netease.com
 * @create_date 2019/4/12
 */
@Target(ElementType.ANNOTATION_TYPE) //作用在注解之上
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    //setXxListener
    String listenerSetter();

    //new View.OnXxListener
    Class<?> listenerType();

    //回调执行方法：onXx()
    String callBaseListener();
}
