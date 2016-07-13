package com.study.grammar;

import java.util.Enumeration;
import java.util.Vector;


class DemoEnumeration {
	public static void main(String[] args) {
		MySession session = new MySession();
		Enumeration myEnumeration = session.getEnum();
		while (myEnumeration.hasMoreElements())
			System.out.println(myEnumeration.nextElement());
	}
}

/**
 * MyEnumeration类实现Enumeration接口
 * 
 * @author 
 * @param <T>
 *
 */
class MyEnumeration<T> implements Enumeration<T> {
	int count; // 计数器
	int length; // 存储的数组的长度
	T[] dataArray; // 存储数据数组的引用

	MyEnumeration(int count, int length, T[] dataArray) {
		this.count = count;
		this.length = length;
		this.dataArray = dataArray;
	}

	public boolean hasMoreElements() {
		return (count < length);
	}

	public T nextElement() {
		return dataArray[count++];
	}
}

/**
 * 用于实例化一个简单的、可以提供enumeration对象
 * 给使用程序的数据结果对象
 * @author Administrator
 *
 */
class MySession {
	String[] data;
	MySession() {
		data = new String[4];
		data[0] = "zero";
		data[1] = "one";
		data[2] = "two";
		data[3] = "three";
	}

	// 返回一个enumeration对象给使用程序
	Enumeration getEnum() {
		return new MyEnumeration<String>(0, data.length, data);
	}
}