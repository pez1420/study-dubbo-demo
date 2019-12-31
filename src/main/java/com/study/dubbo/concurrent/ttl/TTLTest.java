package com.study.dubbo.concurrent.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pez1420@gmail.com
 * @version $Id: TTLTest.java v 0.1 2019/7/31 5:07 PM pez1420 Exp $$
 */
public class TTLTest {

    public static void main(String[] args) throws InterruptedException {

    }


    public static void testTTlTask() {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<>();
        parent.set("old");

        Callable ttlCallable = TtlCallable.get(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), parent.get()));
                return null;
            }
        });

        for (int i = 0; i < 5; i++) {
            executorService.submit(ttlCallable);
        }
    }

}
