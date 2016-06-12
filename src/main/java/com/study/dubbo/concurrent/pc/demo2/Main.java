package com.study.dubbo.concurrent.pc.demo2;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();
		Add add = new Add(lock);
		Subtract sub = new Subtract(lock);
		
		ThreadSubtract sub1Thread = new ThreadSubtract(sub);
		sub1Thread.setName("sub1Thread");
		sub1Thread.start();
		
		ThreadSubtract sub2Thread = new ThreadSubtract(sub);
		sub2Thread.setName("sub2Thread");
		sub2Thread.start();

		ThreadSubtract sub3Thread = new ThreadSubtract(sub);
		sub3Thread.setName("sub3Thread");
		sub3Thread.start();
		
		Thread.sleep(1000);
		
		ThreadAdd addThread = new ThreadAdd(add);
		addThread.setName("addThread");
		addThread.start();
		
	}
}
