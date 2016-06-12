package com.study.dubbo.concurrent.pc.demo1;

public class ThreadAdd extends Thread {

	private Add add;

	public ThreadAdd(Add add) {
		super();
		this.add = add;
	}

	@Override
	public void run() {
		add.add();
	}
	
	
}
