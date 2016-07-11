package com.study.dubbo.concurrent.queue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;;

public class TestQueue {
	
	@Test
	public void test_ConcurrentLinkedQueue() {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		queue.offer("a");
		queue.offer("b");
		queue.add("c");
	}
	
	@Test
	public void test_PriorityQueue() {
		
	
	}
	
	@Test
	public void test_DelayQueue() throws InterruptedException{
		final ReentrantLock lock = new ReentrantLock();
		lock.lockInterruptibly();
		try {
			final long NANO_ORIGIN = System.nanoTime();
			Thread.sleep(10);
			final long end = System.nanoTime();
			System.out.println(end - NANO_ORIGIN);
			TimeUnit unit = TimeUnit.NANOSECONDS;
			System.out.println(unit.convert(end - NANO_ORIGIN, TimeUnit.NANOSECONDS));
		}finally {
			lock.unlock();
		}
		
		
	}

}
