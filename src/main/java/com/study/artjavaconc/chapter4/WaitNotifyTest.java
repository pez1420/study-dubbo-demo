package com.study.artjavaconc.chapter4;

public class WaitNotifyTest {

	public static void main(String[] args) {
		String o = new String();
		synchronized (o) {
			for (int i = 0; i < 5; i++)
				System.out.println(i);
		}
	}

}
