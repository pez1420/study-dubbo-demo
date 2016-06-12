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
					System.out.println(Thread.currentThread().getName() + "����wating״̬");
					lock.wait();
				}
				DataPool.list.remove(0);
				System.out.println(Thread.currentThread().getName() + ": �Ƴ���һ��Ԫ��" );

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
