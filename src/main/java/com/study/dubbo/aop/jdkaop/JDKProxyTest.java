package com.study.dubbo.aop.jdkaop;

import java.lang.reflect.Proxy;

import com.study.dubbo.aop.TargetService;
import com.study.dubbo.aop.TargetServiceImpl;
public class JDKProxyTest {

    public static void main(String[] args) {
        // 希望被代理的目标业务类
        TargetService target = new TargetServiceImpl();
        // 将目标类和横切类编织在一起
        MyInvocationHandler handler = new MyInvocationHandler(target);
        // 创建代理实例
        //Proxy.newProxyInstance 实现源码地址:http://rejoy.iteye.com/blog/1627405
        TargetService proxy = (TargetService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//目标类的类加载器
                target.getClass().getInterfaces(),//目标类的接口
                handler);//横切类

        //System.out.println(target.getClass().getClassLoader());
        //System.out.println(String.class.getClassLoader());
        proxy.say("鲁友炳");
    }
}
