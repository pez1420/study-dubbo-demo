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
				//�������̣߳����ô��̵߳�interrupt()����ʱ��������߳�����һ���жϱ�־    
                //sleep,wait�ȷ������������־λ��ͬʱ���׳�InterruptedException��������̵߳��жϱ�־    
                //������쳣�ε���Thread.currentThread().isInterrupted()����Ϊfalse     
				System.out.println(new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(new Date()));
				Thread.currentThread().sleep(4000);
			} catch (InterruptedException e) {
				//false
				//���������⺯�����磺Object.wait,Thread.sleep�����׳��쳣�⣬��������߳��ж�״̬����˿���������Ҫ�����̵߳��ж�״̬    
				//���������̵߳��жϱ�־ 
				Thread.currentThread().interrupt();
			}
		}
	}
	
    public void cancel() {    
    	this.interrupt();
    }    

}
