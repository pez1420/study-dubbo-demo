package com.study.dubbo.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntgerTest {
	static AtomicInteger i = new AtomicInteger(1);
	
	static int[] value = {1,2,3};
	static AtomicIntegerArray arr = new AtomicIntegerArray(value);
	
	public static void main(String[] args) {
		System.out.println(i.getAndDecrement());
		System.out.println(i.get());

	}

}
