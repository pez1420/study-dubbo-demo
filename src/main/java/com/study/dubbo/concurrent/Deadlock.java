package com.study.dubbo.concurrent;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Deadlock {

	private static String A = "A";
	private static String B = "B";
	
	public static void main(String[] args) {
		//ÄÚ´æÒç³ö¼ÓËÀËø
		Vector v = new Vector();
		for (int i = 0; i < 1000000; i++) {
			Object o = new Object();
			v.add(o);
			o = null;
		}
		
		AtomicInteger a = new AtomicInteger();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				synchronized (A) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (B) {
						System.out.println("BBBBBBBBBBBBB");
					}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				synchronized (B) {
					synchronized (A) {
						System.out.println("AAAAAAAAA");
					}
				}
			}
		});
		
		t1.start();
		t2.start();
	}

}
