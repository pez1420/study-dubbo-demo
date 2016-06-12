package com.study.dubbo.concurrent.thread.interrupt;

import java.util.LinkedList;
import java.util.concurrent.Executors;

public class TestInterruptMain {

	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.setName("#abc");
		myThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�ж�mythread�߳�����һ���жϱ�־
		myThread.cancel();
		
		System.out.println(myThread.isInterrupted());
		//Executors.newFixedThreadPool(6)	;
		//LinkedList<String> myList = new LinkedList<String>();
	}

}
