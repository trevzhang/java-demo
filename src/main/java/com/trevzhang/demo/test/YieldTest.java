package com.trevzhang.demo.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Trevor Zhang
 * @since 2020/11/20
 */
public class YieldTest extends Thread {

    private final AtomicInteger value;

    public YieldTest(String name, AtomicInteger value) {
        super(name);
        this.value = value;
    }

    @Override
    public void run() {
        while (value.get() <= 50) {
            synchronized (YieldTest.class) {
                value.getAndIncrement();
                System.out.println("" + this.getName() + "-----" + value.get());
            }
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (value.get() == 30) {
                System.out.println("Thread yield");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        AtomicInteger value = new AtomicInteger(0);
        YieldTest yt1 = new YieldTest("张三", value);
        YieldTest yt2 = new YieldTest("李四", value);
        yt1.start();
        yt2.start();
    }
}