package com.study.dubbo.concurrent.pc.demo1;

import java.util.Random;

public class Add {

	private Object lock;

	public Add(Object lock) {
		this.lock = lock;
	}

	public void add() {
		synchronized (lock) {
			String tmp = "" + new Random().nextInt(100);
			DataPool.list.add(tmp);
			System.out.println(Thread.currentThread().getName() + ": Ôö¼Ó" + tmp);
			lock.notifyAll();
		}
	}
}
