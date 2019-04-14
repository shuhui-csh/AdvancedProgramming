package com.csh.inject;

import android.service.voice.VoiceInteractionService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author ChenShuHui
 * @className ListenerInvocationHandler
 * @email chenshuhui@corp.netease.com
 * @create_date 2019/4/12
 */
public class ListenerInvocationHandler implements InvocationHandler {

    //需要拦截的对象
    private Object target;
    private HashMap<String, Method> methodHashMap = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target != null) {
            String methodName = method.getName();
            method = methodHashMap.get(methodName);
            if (method != null) {
                if (method.getParameterTypes().length == 0) {
                    return method.invoke(target);
                }
                return method.invoke(target, args);
            }
        }

        return null;
    }

    /**
     * @param methodName
     * @param method
     */
    public void addMethod(String methodName, Method method) {
        methodHashMap.put(methodName, method);
    }
}
