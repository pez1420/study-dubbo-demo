package com.study.dubbo.concurrent.pc.demo3;

public class ThreadB extends Thread {

	private MyService myService;

	public ThreadB(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		this.myService.signal();;
	}
	
}
