package com.study.dubbo.aop.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{
    Enhancer enhancer = new Enhancer();
    
    public Object getProxy(@SuppressWarnings("rawtypes") Class clazz) {
        //设置需要创建的子类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args,
            MethodProxy proxy) throws Throwable {
    	
        System.out.println("下一位请登台发言！");
        
        //目标方法调用
        Object result = proxy.invokeSuper(obj, args);
        
        System.out.println("大家掌声鼓励！");
        
        return result;
    }    
}
