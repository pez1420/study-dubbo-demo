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
 * MyEnumeration��ʵ��Enumeration�ӿ�
 * 
 * @author 
 * @param <T>
 *
 */
class MyEnumeration<T> implements Enumeration<T> {
	int count; // ������
	int length; // �洢������ĳ���
	T[] dataArray; // �洢�������������

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
 * ����ʵ����һ���򵥵ġ������ṩenumeration����
 * ��ʹ�ó�������ݽ������
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

	// ����һ��enumeration�����ʹ�ó���
	Enumeration getEnum() {
		return new MyEnumeration<String>(0, data.length, data);
	}
}