package com.study.dubbo.aop.jdkaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;
	
	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	/***
	 * ��һ��������ʵ���Ǵ����Ĵ��������
	 * ������ô������say����ʱ�������õ���InvokeHandler��invoke�����������Ը����ײδ��˽�ȥ
	 * ����ĵ�һ������obj��ʵ���Կ���û���ô���
	 * 
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("-----------�����ʺ�֮ǰ����------------");
		
		Object obj = method.invoke(target, args);
		
		System.out.println("-----------�����ʺ�֮������------------");

		return obj;
	}

}
