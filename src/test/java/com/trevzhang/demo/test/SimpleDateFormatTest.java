package com.trevzhang.demo.test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
 * 测试SimpleDateFormat
 *
 * @author zhangchunguang.zcg
 * @since 2023/8/28 17:13
 */
@Slf4j
public class SimpleDateFormatTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

    // 创建 ThreadLocal 对象，并设置默认值（new SimpleDateFormat）
    private static final ThreadLocal<SimpleDateFormat> sdfThreadLocal =
        ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));

    // JDK8新的日期格式化类，线程安全
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss");

    @Test
    public void testConcurrentSdf() throws ExecutionException, InterruptedException {
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
                    // 时间格式化
                    return sdf.format(date);
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

    @Test
    public void testSynchronizedSdf() throws ExecutionException, InterruptedException {
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
                    // 时间格式化
                    synchronized (sdf){
                        return sdf.format(date);
                    }
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

    @Test
    public void testDateTimeFormatter() throws ExecutionException, InterruptedException {
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
                    // 将 Date 转换成 JDK 8 中的时间类型 LocalDateTime
                    LocalDateTime localDateTime =
                        LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    // 时间格式化
                    return dateTimeFormatter.format(localDateTime);
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
