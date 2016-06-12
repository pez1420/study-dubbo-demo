package com.study.artjavaconc.chapter4;

public class Daemon {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					SleepUtils.second(10);
				}finally {
					System.out.println(Thread.currentThread().getName() + " to finally block!");
				}
			}
		}, "DaemonRunnerThread");
		
		thread.setDaemon(true);
		thread.start();
	}
	
	

}
