package com.study.dubbo.concurrent.queue;

import java.util.concurrent.ConcurrentLinkedQueue;;

public class TestQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		queue.offer("a");
		queue.offer("b");
	}

}
