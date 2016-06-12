package com.study.dubbo.concurrent.thread.interrupt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				//当其他线程，调用此线程的interrupt()方法时，会给此线程设置一个中断标志    
                //sleep,wait等方法会检测这个标志位，同时会抛出InterruptedException，并清除线程的中断标志    
                //因此在异常段调用Thread.currentThread().isInterrupted()返回为false     
				System.out.println(new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(new Date()));
				Thread.currentThread().sleep(4000);
			} catch (InterruptedException e) {
				//false
				//由于阻塞库函数，如：Object.wait,Thread.sleep除了抛出异常外，还会清除线程中断状态，因此可能在这里要保留线程的中断状态    
				//重新设置线程的中断标志 
				Thread.currentThread().interrupt();
			}
		}
	}
	
    public void cancel() {    
    	this.interrupt();
    }    

}
