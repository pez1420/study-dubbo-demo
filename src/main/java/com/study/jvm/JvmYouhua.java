package com.study.jvm;

public class JvmYouhua {

	public static void main(String[] args) {
		Runnable run1 = new Runnable() {
			long i = 0 ;
			@Override
			public void run() {
				while(true) {
					i++;
					System.out.println(i);
				}
				
			}
		};
		
		Runnable run2 = new Runnable() {
			long i = 0 ;
			@Override
			public void run() {
				while(true) {
					i++;
					System.out.println(i%10000);
				}
				
			}
		};
		
		new Thread(run1).start();
		new Thread(run2).start();
	}

}
