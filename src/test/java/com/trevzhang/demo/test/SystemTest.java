package com.trevzhang.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ForkJoinPool的无参构造方法中, 取得是当前系统平台可用的cpu线程数, 作为线程池的参数
 *
 * @author Trevor Zhang
 * @since 2020/10/19 11:03 上午
 */
public class SystemTest {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService forkJoinPool = Executors.newWorkStealingPool();
        ExecutorService scheduledPool = Executors.newScheduledThreadPool(4);
        forkJoinPool.submit(() -> System.out.println(1));
        forkJoinPool.execute(() -> System.out.println(1));
    }
}
