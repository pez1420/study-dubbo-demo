package com.study.dubbo.grammar;

import java.util.Arrays;

public class ArrayDemo {
	
	public static void mainv() {
		int[] arr1 = { 1, 2, 3, 4, 5 };
		int[] arr2 = Arrays.copyOf(arr1, arr1.length);

		for (int i = 0; i < arr2.length; i++)
			System.out.print(arr2[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		
			ArrayDemo demo = new ArrayDemo();
			
			//System.gc();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized (ArrayDemo.class) {
				try {
					ArrayDemo.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
	}
}