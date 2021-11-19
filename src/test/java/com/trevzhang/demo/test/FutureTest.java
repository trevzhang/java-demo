package com.trevzhang.demo.test;

import cn.hutool.json.JSONUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 春火
 * @since 2021/4/12 4:10 下午
 */
public class FutureTest {

    private static final ExecutorService executorService = new ThreadPoolExecutor(5, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),
        new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "test-future-" + threadNumber.incrementAndGet());
            }
        });

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<Boolean> future = doTask();
        while (!future.isDone()) {
            System.out.println("waiting...");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(future.get());
//        executorService.shutdown();
    }

    public static Future<Boolean> doTask() {
        return executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return true;
        });
    }

}
