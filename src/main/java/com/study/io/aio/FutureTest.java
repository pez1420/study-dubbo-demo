package com.study.io.aio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

	public static class Task implements Runnable {
		@Override
		public void run() {
			System.out.println("abcdefg!");
		}
	}
	
	public static void main(String[] args) {
		final ExecutorService executor = Executors.newCachedThreadPool();
		List<Future<String>> result = new ArrayList<Future<String>>();
		for (int i = 0; i < 100; i++) {
			result.add(executor.submit(new CallTask()));
		}
		for (Future<String> future : result) {
			try {
				while (true) {
					if (future.isDone() && !future.isCancelled()) {
						System.out.println("Future:" + future + ",Result:" + future.get());
						break;
					} else {
						System.out.println("It have not finished!!!");
						Thread.sleep(1000);
					}
				}
				
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				if (!executor.isShutdown()) {
					executor.shutdown();
				}
			}
		}));
		
	}
	
	
	public static class CallTask implements Callable<String> {
		public String call() throws Exception {
			System.out.println("abcdefg!");
			return new java.util.Date(System.currentTimeMillis()).toString();
		}
	}
	
	
}
