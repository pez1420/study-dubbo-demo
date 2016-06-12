package com.study.dubbo.concurrent.pc.demo3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void await() {
		lock.lock();
		try {
			System.out.println("await的时间为" + System.currentTimeMillis());
			condition.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void signal() {
		lock.lock();
		try {
			System.out.println("signal的时间为" + System.currentTimeMillis());

			condition.signal();
		}finally {
			lock.unlock();
		}
	}
}