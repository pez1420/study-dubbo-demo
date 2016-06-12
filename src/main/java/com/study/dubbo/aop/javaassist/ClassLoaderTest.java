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
		
		// 此时三个ClassLoader是同一个对象 
		// 当前线程的类加载器
		System.out.println(Thread.currentThread().getContextClassLoader()); 
		// 当前类的类加载器
		System.out.println(ClassLoaderTest.class.getClassLoader()); 
		// // 系统初始的类加载器 
		System.out.println(ClassLoader.getSystemClassLoader());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
