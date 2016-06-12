package com.study.dubbo.grammar;

public class TestRuntimeShutdown {

	public static void main(String[] args) {

		Thread shutdownThread = new Thread() {
			@Override
			public void run() {
				System.out.println("虚拟机关闭执行该shutdownHook线程!");
			}
		};
		
		Runtime.getRuntime().addShutdownHook(shutdownThread);
		
		Runnable thread1= new Runnable() {
			public void run() {
				try {
					System.out.println("处于线程1的状态!");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Runnable thread2 = new Runnable() {
			public void run() {
				try {
					System.out.println("处于线程2的状态!");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		new Thread(thread1).start();
		new Thread(thread2).start();
	}

}
