package com.study.dubbo.concurrent.pc;

/**
 * �ٶ������������߳�Ҫ��ӡ1��9��9�����֣�Ҫ���һ���̴߳�ӡ1��2��3Ȼ��ֹͣ��ӡ��
 * ���߳�2��ӡ4��5��6��Ȼ���߳�2ֹͣ��ӡ��֪ͨ�߳�1������ӡ7��8��9
 * @author Administrator
 *
 */
public class WaitNotifyDemo {
	private volatile int val = 1;

	private synchronized void printAndIncrease() {
		System.out.println(Thread.currentThread().getName() + " prints " + val);
		val++;
	}

	// print 1,2,3 7,8,9
	public class PrinterA implements Runnable {
		public void run() {
			while (val <= 3) {
				printAndIncrease();
			}

			// print 1,2,3 then notify printerB
			synchronized (WaitNotifyDemo.this) {
				System.out.println("PrinterA printed 1,2,3; notify PrinterB");
				WaitNotifyDemo.this.notify();
			}

			try {
				while (val <= 6) {
					synchronized (WaitNotifyDemo.this) {
						System.out.println("wait in printerA");
						WaitNotifyDemo.this.wait();
					}
				}
				System.out.println("wait end printerA");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (val <= 9) {
				printAndIncrease();
			}
			System.out.println("PrinterA exits");
		}
	}
	// print 4,5,6 after printA print 1,2,3
	public class PrinterB implements Runnable {

		public void run() {
			while (val < 3) {
				synchronized (WaitNotifyDemo.this) {
					try {
						System.out
								.println("printerB wait for printerA printed 1,2,3");
						WaitNotifyDemo.this.wait();
						System.out
								.println("printerB waited for printerA printed 1,2,3");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			while (val <= 6) {
				printAndIncrease();
			}

			System.out.println("notify in printerB");
			synchronized (WaitNotifyDemo.this) {
				WaitNotifyDemo.this.notify();
			}
			System.out.println("notify end printerB");
			System.out.println("PrinterB exits.");
		}
	}
	public static void main(String[] args) {
		WaitNotifyDemo demo = new WaitNotifyDemo();
		demo.doPrint();
	}

	private void doPrint() {
		PrinterA pa = new PrinterA();
		PrinterB pb = new PrinterB();
		Thread a = new Thread(pa);
		a.setName("printerA");
		Thread b = new Thread(pb);
		b.setName("printerB");
		// ������b�߳���ִ�У�����b�߳��п��ܵò�������ִ�в���wait����a�߳�һֱ������������notify��
		b.start();
		a.start();
	}
}
