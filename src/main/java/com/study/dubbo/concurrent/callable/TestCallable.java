package com.study.dubbo.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class CallThread implements Callable<Integer> {
	
	public Integer call() {
		int i = 0;
		for (; i < 60; i++) {
			System.out.println(Thread.currentThread().getName() + "ѭ������i��ֵ�� : " + i);
		}
		return i;
	}
}

public class TestCallable {

	public static void main(String[] args) {
		// ����Callable����
		CallThread call = new CallThread();
		// ʹ��FutrueTask����װCallable����
		FutureTask<Integer> task = new FutureTask<Integer>(call);
		for (int i = 0; i < 60; i++) {
			System.out.println(Thread.currentThread().getName() + "-------" + i);
			if (i == 10) {
				// ʵ���ϻ�����Callable���󴴽��������߳�
				new Thread(task, "�з���ֵ���߳�").start(); 
			}
		}
		
		try {
			System.out.println("���̵߳ķ���ֵ ��" + task.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
