package com.trevzhang.demo.concurrent.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Trevor Zhang
 * @since 2020/10/14 10:13 下午
 */
public class Demo {

    //访问计数
    static int count = 0;

    public void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        while (!compareAndSwap(count, count + 1)) {}
    }

    /**
     * @param expect 期望值
     * @param newVal 新值
     * @return 成功与否
     */
    public static synchronized boolean compareAndSwap(int expect, int newVal) {
        // 判断count当前值是否和期望值一致
        if (getCount() == expect) {
            count = newVal;
            return true;
        }
        return false;
    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int threadSize = 1000;
        CountDownLatch latch = new CountDownLatch(threadSize);
        Demo demo = new Demo();
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
