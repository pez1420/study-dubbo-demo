package com.study.dubbo.concurrent.pc.demo2;

public class ThreadSubtract extends Thread {

	private Subtract subtract;

	public ThreadSubtract(Subtract subtract) {
		super();
		this.subtract = subtract;
	}

	@Override
	public void run() {
		subtract.subtract();
	}
	
	
}
