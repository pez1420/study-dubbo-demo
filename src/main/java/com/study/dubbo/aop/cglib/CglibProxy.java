package com.study.dubbo.aop.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{
    Enhancer enhancer = new Enhancer();
    
    public Object getProxy(@SuppressWarnings("rawtypes") Class clazz) {
        //������Ҫ����������
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //ͨ���ֽ��뼼����̬��������ʵ��
        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args,
            MethodProxy proxy) throws Throwable {
    	
        System.out.println("��һλ���̨���ԣ�");
        
        //Ŀ�귽������
        Object result = proxy.invokeSuper(obj, args);
        
        System.out.println("�������������");
        
        return result;
    }    
}
