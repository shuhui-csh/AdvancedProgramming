package com.csh.inject.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ChenShuHui
 * @className OnClick
 * @email chenshuhui@corp.netease.com
 * @create_date 2019/4/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnClickListener", listenerType = View.OnClickListener.class, callBaseListener = "onClick")
public @interface OnClick {
    int[] value();
}
