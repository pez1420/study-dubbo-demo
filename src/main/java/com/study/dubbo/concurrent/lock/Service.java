package com.study.dubbo.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {
	private ReentrantLock lock = new ReentrantLock();
	private Condition myCondition = lock.newCondition();
	
	public void waitMethod() {
		lock.lock();
		try {
			myCondition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void notifyMethod() {
		lock.lock();
		try {
			System.out.println("myconditionËø¶¨ÊýÄ¿:" + lock.getWaitQueueLength(myCondition));
			myCondition.signalAll();
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final Service service = new Service();
		Runnable runable = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		
		Thread[] threadArray = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threadArray[i] = new Thread(runable);
		}
		
		for (int i = 0; i < 10; i++) {
			threadArray[i].start();
		}
			
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.notifyMethod();
		
	}
}
