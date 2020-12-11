package com.trevzhang.demo.concurrent.collectionDemo;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CollectionDemo01 {

    public static void main(String[] args) throws InterruptedException {
//        sleepImpl();
        countDownLatchImpl();
    }

    private static void sleepImpl() throws InterruptedException {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque();
        //添加数据
        Thread[] add = new Thread[100];
        for (int i = 0; i < 100; i++) {
            add[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    list.add(Thread.currentThread().getName() + ":Element " + j);
                }
            });
            add[i].start();
        }

        // 等待前面填充完毕
        TimeUnit.SECONDS.sleep(1);
        System.out.println("after add size:" + list.size());

        Thread[] poll = new Thread[100];
        for (int i = 0; i < 100; i++) {
            poll[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    list.pollLast();
                }
            });
            poll[i].start();
        }

        // 等待前面移除完毕
        TimeUnit.SECONDS.sleep(1);
        System.out.println("after poll size:" + list.size());
    }

    private static void countDownLatchImpl() throws InterruptedException {
        final int THREAD_TOTAL = 100;

        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque();
        CountDownLatch addLatch = new CountDownLatch(THREAD_TOTAL);
        //添加数据
        Thread[] add = new Thread[THREAD_TOTAL];
        for (int i = 0; i < THREAD_TOTAL; i++) {
            add[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    list.add(Thread.currentThread().getName() + ":Element " + j);
                }
                addLatch.countDown();
            });
            add[i].start();
        }

        // 等待前面填充完毕
        addLatch.await();
        System.out.println("after add size:" + list.size());

        CountDownLatch pollLatch = new CountDownLatch(THREAD_TOTAL);
        Thread[] poll = new Thread[THREAD_TOTAL];
        for (int i = 0; i < THREAD_TOTAL; i++) {
            poll[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    list.pollLast();
                }
                pollLatch.countDown();
            });
            poll[i].start();
        }

        // 等待前面移除完毕
        pollLatch.await();
        System.out.println("after poll size:" + list.size());
    }
}
