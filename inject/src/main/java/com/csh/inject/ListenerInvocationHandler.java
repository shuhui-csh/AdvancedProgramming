package com.csh.inject;

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
            //获得需要拦截方法的方法名
            String methodName = method.getName();
            //通过需要拦截方法名获得需要替换成的方法
            method = methodHashMap.get(methodName);
            if (method != null) {
                //如果原来方法没有参数
                if (method.getParameterTypes().length == 0) {
                    return method.invoke(target);
                }
                return method.invoke(target, args);
            }
        }

        return null;
    }

    /**
     * 将需要拦截的方法名——需要替换成的自定义方法 对加入集合
     *
     * @param methodName 需要拦截的方法名 比如：onClick()
     * @param method     需要执行的自定义方法  比如：show()
     */
    public void addMethod(String methodName, Method method) {
        methodHashMap.put(methodName, method);
    }
}
