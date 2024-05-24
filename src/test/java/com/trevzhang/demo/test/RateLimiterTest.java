package com.trevzhang.demo.test;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Haruki
 * @since 2024/5/24 18:51
 */
public class RateLimiterTest {

    @Test
    public void testRateLimiter() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1000, 1000, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));
        TimeUnit.SECONDS.sleep(1);

        AtomicInteger limited = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.submit(() ->
            {
                long start = System.currentTimeMillis();
                boolean acq = rateLimiter.tryAcquire(10, TimeUnit.MILLISECONDS);
                if (acq) {
                    System.out.println("等待时间: " + (System.currentTimeMillis() - start) + "ms, i: " + finalI);
                } else {
                    limited.incrementAndGet();
                    //				System.out.println("failed, acq = " + acq + ", i = " + i);
                }
            });
        }
        System.out.println("limited: " + limited);
    }
}
