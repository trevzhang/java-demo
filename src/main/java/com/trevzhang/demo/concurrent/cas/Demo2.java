package com.trevzhang.demo.concurrent.cas;

import cn.hutool.core.util.RandomUtil;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Trevor Zhang
 * @since 2020/10/14 10:13 下午
 */
public class Demo2 {

    //访问计数
    static AtomicInteger count = new AtomicInteger(0);

    public void request() throws InterruptedException {
        // 模拟用户随机访问消耗
        TimeUnit.MILLISECONDS.sleep(RandomUtil.randomInt() & 0x11);
        count.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int threadSize = 1000;
        CountDownLatch latch = new CountDownLatch(threadSize);
        Demo2 demo = new Demo2();
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        demo.request();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            });
            thread.start();
        }
        latch.await();
        System.out.println((System.currentTimeMillis() - start) + "ms");
        System.out.println("count: " + count);
    }
}
