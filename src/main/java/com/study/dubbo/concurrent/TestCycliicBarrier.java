package com.study.dubbo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCycliicBarrier {

	public static CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
		@Override
		public void run() {
			System.out.println(3);
		}
	});
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("2:" + barrier.getNumberWaiting());
					Thread.sleep(2000);
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(1);
			}
		}).start();
		
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(2);
		
	}

}
