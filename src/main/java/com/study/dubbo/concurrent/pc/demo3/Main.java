package com.study.dubbo.concurrent.pc.demo3;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		MyService myService = new MyService();
		ThreadA a = new ThreadA(myService);
		a.start();
		
		Thread.sleep(1000);
		
		myService.signal();
	}

}
