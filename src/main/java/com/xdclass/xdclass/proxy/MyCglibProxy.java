package com.xdclass.xdclass.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCglibProxy implements MethodInterceptor {
    //目标类
    private Object targetObj;

    //绑定关系
    public Object newProxyInstance(Object targetObj) {
        this.targetObj = targetObj;
        Enhancer enhancer = new Enhancer();
        //设置代理类的父类(目标类)
        enhancer.setSuperclass(this.targetObj.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类(代理对象)
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        try {
            System.out.println("通过cglib动态代理"+ method.getName()+" start");
            result = methodProxy.invokeSuper(o, objects);
            System.out.println("通过cglib动态代理"+ method.getName()+" end");
        }catch (Exception e) {

        }
        return result;
    }
}
