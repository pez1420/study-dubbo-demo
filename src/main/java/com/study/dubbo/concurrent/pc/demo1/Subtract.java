package com.study.dubbo.concurrent.pc.demo1;

public class Subtract {

	private Object lock;

	public Subtract(Object lock) {
		this.lock = lock;
	}

	public void subtract() {
		try {
			synchronized (lock) {
				if (DataPool.list.size() == 0) {
					System.out.println(Thread.currentThread().getName() + "处于wating状态");
					lock.wait();
				}
				DataPool.list.remove(0);
				System.out.println(Thread.currentThread().getName() + ": 移除第一个元素" );

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
