package com.study.dubbo.aop.jdkaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;
	
	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	/***
	 * 第一个参数其实就是创建的代理类对象
	 * 当你调用代理类的say方法时，他调用的是InvokeHandler的invoke方法，并把自个做首参传了进去
	 * 这里的第一个参数obj其实可以看作没有用处的
	 * 
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("-----------向大家问好之前内容------------");
		
		Object obj = method.invoke(target, args);
		
		System.out.println("-----------向大家问好之后内容------------");

		return obj;
	}

}
