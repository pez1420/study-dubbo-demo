package com.study.io.aio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncController {

	//�̳߳�
	private ExecutorService executorService;

	//�����첽�����Future
	private FutureContext<String> context;

	public AsyncController() {
		this.executorService = Executors.newFixedThreadPool(100);
		this.context = new FutureContext<String>();
	}

	public static void main(String[] args) {
		//�����첽����
		AsyncController controller = new AsyncController();
		controller.startAsyncCompution();

		//�����첽����������̣߳����߳�ɨ���첽����Futrue��״̬������Ѿ���ɣ�������첽������
		OutputResult output = new OutputResult();
		output.setFutureContext(controller.getFutureContext());
		Thread resultThread = new Thread(output);
		resultThread.start();
	}

	public FutureContext<String> getFutureContext() {
		return this.context;
	}

	public void startAsyncCompution() {
		/**
		 * ����100���첽���㣬ÿ���첽�����߳����sleep������ģ������ʱ��
		 */
		final Random random = new Random();
		for (int i = 0; i < 100; i++) {
			Future<String> future = this.executorService
					.submit(new Callable<String>() {
						@Override
						public String call() throws Exception {
							int randomInt = random.nextInt(10);
							Thread.sleep(randomInt * 1000);
							return "" + randomInt;
						}
					});
			//ÿ���첽����Ľ�������context��
			this.context.addFuture(future);
		}
	}

	public static class FutureContext<T> {

		private List<Future<T>> futureList = new ArrayList<Future<T>>();

		public void addFuture(Future<T> future) {
			this.futureList.add(future);
		}

		public List<Future<T>> getFutureList() {
			return this.futureList;
		}
	}

	public static class OutputResult implements Runnable {

		private FutureContext<String> context;

		public void setFutureContext(FutureContext<String> context) {
			this.context = context;
		}

		@Override
		public void run() {
			System.out.println("start to output result:");
			List<Future<String>> list = this.context.getFutureList();

			for (Future<String> future : list) {
				this.outputResultFromFuture(future);
			}

			System.out.println("finish to output result.");
		}

		private void outputResultFromFuture(Future<String> future) {
			try {
				while (true) {
					if (future.isDone() && !future.isCancelled()) {
						System.out.println("Future:" + future + ",Result:"
								+ future.get());
						break;
					} else {
						Thread.sleep(1000);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
