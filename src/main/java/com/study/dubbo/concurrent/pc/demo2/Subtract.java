package com.study.dubbo.concurrent.pc.demo2;

public class Subtract {

	private Object lock;

	public Subtract(Object lock) {
		this.lock = lock;
	}

	public void subtract() {
		try {
			synchronized (lock) {
				while (DataPool.list.size() == 0) {
					System.out.println(Thread.currentThread().getName() + "wating start×´Ì¬");
					lock.wait();
					System.out.println(Thread.currentThread().getName() + "wating end ×´Ì¬");

				}
				DataPool.list.remove(0);
				System.out.println(Thread.currentThread().getName() + ": ÒÆ³ýµÚÒ»¸öÔªËØ" );

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
