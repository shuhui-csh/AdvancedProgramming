package com.csh.inject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ChenShuHui
 * @className InjectView
 * @email chenshuhui@corp.netease.com
 * @create_date 2019/4/11
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {
    int value();
}
