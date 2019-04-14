package com.csh.inject;

import android.app.Activity;
import android.view.View;

import com.csh.inject.annotation.ContentView;
import com.csh.inject.annotation.EventBase;
import com.csh.inject.annotation.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ChenShuHui
 * @className InjectManager
 * @email chenshuhui@corp.netease.com
 * @create_date 2019/4/11
 */
public class InjectManager {

    public static void inject(Activity activity) {
        //布局注入
        injectLayout(activity);
        //控件注入
        injectView(activity);
        //事件注入
        injectEvent(activity);
    }

    private static void injectEvent(Activity activity) {
        //获取类
        Class<? extends Activity> clazz = activity.getClass();
        //获取类的所有方法
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            //获取 每个方法的注解
            Annotation[] annotations = method.getAnnotations();
            //遍历每个方法的注解
            for (Annotation annotation : annotations) {
                //获取这个注解上的注解类型（其实还是一个注解）
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    //事件的3个规律
                    String listenerSetter = eventBase.listenerSetter();
                    Class<?> listenerType = eventBase.listenerType();
                    String callBaseListener = eventBase.callBaseListener();

                    //注解的值
                    try {
                        //通过annotationType获取onClick注解的value的值
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        //执行value方法获得注解的值
                        int[] viewIds = (int[]) valueMethod.invoke(annotation);

                        ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                        handler.addMethod(callBaseListener, method);
                        //通过代理，换掉本来要执行的callBaseListener（比如onClick）,执行我自定义method(比如show)
                        Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);

                        for (int viewId : viewIds) {
                            //控件的赋值过程
                            View view = activity.findViewById(viewId);
                            //获取方法
                            Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                            //执行方法
                            setter.invoke(view, listener);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void injectView(Activity activity) {
        //获取类
        Class<? extends Activity> clazz = activity.getClass();
        //获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        //循环，拿到每个属性
        for (Field field : fields) {
            //获取每个属性上的注解
            InjectView injectView = field.getAnnotation(InjectView.class);
            //获取注解的值
            if (injectView != null) {//并不是每个属性都有注解
                int viewId = injectView.value();
                //执行方法：findViewById
                try {
                    Method method = clazz.getMethod("findViewById", int.class);
                    Object view = method.invoke(activity, viewId);
                    //将执行方法的返回值赋值给全局变量field
                    field.setAccessible(true);//设置private访问权限
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectLayout(Activity activity) {
        //获取类
        Class<? extends Activity> clazz = activity.getClass();
        //获取这个类上的实例
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            //获取布局文件id
            int layoutId = contentView.value();

            try {
                //获取setContentView方法
                Method method = clazz.getMethod("setContentView", int.class);
                //执行方法
                method.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
