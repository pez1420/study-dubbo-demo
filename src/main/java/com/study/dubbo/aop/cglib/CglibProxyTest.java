package com.study.dubbo.aop.cglib;

import com.study.dubbo.aop.TargetService;
import com.study.dubbo.aop.TargetServiceImpl;

public class CglibProxyTest {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过动态生成子类的方式创建代理类
        TargetService target = (TargetService) proxy.getProxy(TargetServiceImpl.class);
        target.say("鲁友炳");
    }
}

