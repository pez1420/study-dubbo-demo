package com.study.dubbo.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class CallThread implements Callable<Integer> {
	
	public Integer call() {
		int i = 0;
		for (; i < 60; i++) {
			System.out.println(Thread.currentThread().getName() + "循环变量i的值是 : " + i);
		}
		return i;
	}
}

public class TestCallable {

	public static void main(String[] args) {
		// 创建Callable对象
		CallThread call = new CallThread();
		// 使用FutrueTask来包装Callable对象
		FutureTask<Integer> task = new FutureTask<Integer>(call);
		for (int i = 0; i < 60; i++) {
			System.out.println(Thread.currentThread().getName() + "-------" + i);
			if (i == 10) {
				// 实际上还是以Callable对象创建并启动线程
				new Thread(task, "有返回值的线程").start(); 
			}
		}
		
		try {
			System.out.println("子线程的返回值 ：" + task.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
