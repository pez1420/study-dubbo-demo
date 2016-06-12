package com.study.dubbo.concurrent.pc.demo2;

public class ThreadAdd extends Thread {

	private Add add;

	public ThreadAdd(Add add) {
		super();
		this.add = add;
	}

	@Override
	public void run() {
		while(true) {
			add.add();
		}
		
	}
	
	
}
