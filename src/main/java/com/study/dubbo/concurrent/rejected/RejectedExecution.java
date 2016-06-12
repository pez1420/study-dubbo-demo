package com.study.dubbo.concurrent.rejected;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectedExecution {
	
	private static class Worker implements Runnable {
		
		public void run() {
			System.out.println(Thread.currentThread().getName() + " is running");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		int corePoolSize = 5;
		int maxPoolSize = 10;
		long keepAliveTime = 5;
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10);
		 //�ܾ�����1�����׳� RejectedExecutionException.
		//RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
		//�ܾ�����2
		//RejectedExecutionHandler handler  = new ThreadPoolExecutor.CallerRunsPolicy(); 
		//�ܾ�����3
		RejectedExecutionHandler handler  = new ThreadPoolExecutor.DiscardOldestPolicy(); 
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
					corePoolSize, 
					maxPoolSize, 
					keepAliveTime, 
					TimeUnit.SECONDS, 
					queue, 
					handler
		);
		
	
		
		for(int i = 0; i < 100; i++) {
			executor.execute(new Worker());
            System.out.println("�̳߳����߳���Ŀ��" + executor.getPoolSize() 
            + ",�����߳���Ŀ" + executor.getCorePoolSize() 
        	+ ",�����еȴ�ִ�е�������Ŀ��" + executor.getQueue().size() 
        	+ "����ִ������������Ŀ��" + executor.getCompletedTaskCount());
		}
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdown();
	}

}
