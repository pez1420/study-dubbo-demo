package com.study.dubbo.spring.xsd;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XsdTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-provider.xml");  
		People p = (People)ctx.getBean("lyb");  
		System.out.println(p.getId());  
		System.out.println(p.getName());  
		System.out.println(p.getAge());
	}

}
