package com.study.spring.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

class MessageStore {
    public void print() {
        System.out.println("hello world!");

    }
}

/**
 * 简单前置AOP
 *
 * @author luyb@servyou.com.cn
 * @version 2016-08-15 16:35
 */
public class SimpleBeforeAdvice implements MethodBeforeAdvice,AfterReturningAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before method " + method.getName());
    }


    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after method " + method.getName());
    }


    public static void main(String[] args) {
        MessageStore messageStore = new MessageStore();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(messageStore);

        MessageStore proxyMessageStore = (MessageStore)pf.getProxy();
        proxyMessageStore.print();
    }

}
