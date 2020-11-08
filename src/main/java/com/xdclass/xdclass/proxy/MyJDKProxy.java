package com.xdclass.xdclass.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyJDKProxy implements InvocationHandler {
    //目标类
    private Object targetObj;

    //获取代理对象
    public Object newProxyInstance(Object targetObj) {
        this.targetObj = targetObj;
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(),
                this);
    }

    /**
     *
     * @param o 目标类
     * @param method 目标类方法
     * @param objects 目标类参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object obj = null;
        try {
            System.out.println("---通过jdk动态代理调用" + method.getName() + ", 打印日志 begin");
            obj = method.invoke(targetObj, objects);
            System.out.println("---通过jdk动态代理调用" + method.getName() + ", 打印日志 end");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
