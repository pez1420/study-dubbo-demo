package com.study.dubbo.concurrent.pc.demo3;

public class ThreadA extends Thread {

	private MyService myService;

	public ThreadA(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		this.myService.await();
	}
	
}
