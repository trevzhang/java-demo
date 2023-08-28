package com.trevzhang.demo.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2023/8/28 17:13
 */
@Slf4j
public class SimpleDateFormatTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

    // 创建 ThreadLocal 对象，并设置默认值（new SimpleDateFormat）
    private static final ThreadLocal<SimpleDateFormat> sdfThreadLocal =
        ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));

    @Test
    public void testConcurrentSdf() throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 创建时间对象
                    Date date = new Date(finalI * 1000);
                    // 执行时间格式化并打印结果
                    System.out.println(sdf.format(date));
                }
            });
        }
    }

    @Test
    public void testThreadLocalSdf() throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<>();
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            Future<String> future = threadPool.submit(new Callable<String>() {
                @Override
                public String call() {
                    // 创建时间对象
                    Date date = new Date(finalI * 1000);
                    // 格式化时间
                    return sdfThreadLocal.get().format(date);
                }
            });
            futures.add(future);
        }

        // 任务执行完之后关闭线程池
        threadPool.shutdown();
        for (Future<String> future : futures) {
            String result = future.get();
            System.out.println(result);
        }
    }
}
