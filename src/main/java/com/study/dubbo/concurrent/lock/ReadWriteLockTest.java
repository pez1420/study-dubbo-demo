package com.study.dubbo.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLockTest {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		try {
			lock.lockInterruptibly();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
