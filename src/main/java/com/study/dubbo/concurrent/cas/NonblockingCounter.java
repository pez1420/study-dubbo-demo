package com.study.dubbo.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class NonblockingCounter {
	private AtomicInteger value;// ǰ���ᵽ����AtomicInteger������ԭ�ӵķ�ʽ�������ͱ�����

	public int getValue() {
		return value.get();
	}

	public int increment() {
		int v;
		do {
			v = value.get();
		} while (!value.compareAndSet(v, v + 1));

		return v + 1;
	}
}
