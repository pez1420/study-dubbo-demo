package com.study.dubbo.concurrent.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class MyTask implements Runnable {
    private int taskNum;
     
    public MyTask(int num) {
        this.taskNum = num;
    }
     
    @SuppressWarnings("static-access")
	public void run() {
        System.out.println("����ִ��task "+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"ִ�����");
    }
}


public class TestThreadPoolExecutor {

	public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
         
        for(int i = 0; i < 15; i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("�̳߳����߳���Ŀ��" + executor.getPoolSize() 
            	+ "�������еȴ�ִ�е�������Ŀ��" + executor.getQueue().size() 
            	+ "����ִ������������Ŀ��" + executor.getCompletedTaskCount());
        }
        executor.shutdown();

	}

}
