package com.study.dubbo.aop.cglib;

import com.study.dubbo.aop.TargetService;
import com.study.dubbo.aop.TargetServiceImpl;

public class CglibProxyTest {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //ͨ����̬��������ķ�ʽ����������
        TargetService target = (TargetService) proxy.getProxy(TargetServiceImpl.class);
        target.say("³�ѱ�");
    }
}

