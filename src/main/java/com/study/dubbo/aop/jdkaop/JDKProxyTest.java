package com.study.dubbo.aop.jdkaop;

import java.lang.reflect.Proxy;

import com.study.dubbo.aop.TargetService;
import com.study.dubbo.aop.TargetServiceImpl;
public class JDKProxyTest {

    public static void main(String[] args) {
        // ϣ���������Ŀ��ҵ����
        TargetService target = new TargetServiceImpl();
        // ��Ŀ����ͺ������֯��һ��
        MyInvocationHandler handler = new MyInvocationHandler(target);
        // ��������ʵ��
        //Proxy.newProxyInstance ʵ��Դ���ַ:http://rejoy.iteye.com/blog/1627405
        TargetService proxy = (TargetService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//Ŀ������������
                target.getClass().getInterfaces(),//Ŀ����Ľӿ�
                handler);//������

        //System.out.println(target.getClass().getClassLoader());
        //System.out.println(String.class.getClassLoader());
        proxy.say("³�ѱ�");
    }
}
