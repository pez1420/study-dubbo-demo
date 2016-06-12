package com.study.dubbo.aop.javaassist;

import org.junit.Test;

import junit.framework.Assert;

public class ClassLoaderTest {
	
	@Test
	public void test_equal_classloader() {
		Assert.assertTrue(Thread.currentThread().getContextClassLoader()
				.equals(ClassLoaderTest.class.getClassLoader()));
		
		Assert.assertTrue(Thread.currentThread().getContextClassLoader()
				.equals(ClassLoader.getSystemClassLoader()));
		
		// ��ʱ����ClassLoader��ͬһ������ 
		// ��ǰ�̵߳��������
		System.out.println(Thread.currentThread().getContextClassLoader()); 
		// ��ǰ����������
		System.out.println(ClassLoaderTest.class.getClassLoader()); 
		// // ϵͳ��ʼ��������� 
		System.out.println(ClassLoader.getSystemClassLoader());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
