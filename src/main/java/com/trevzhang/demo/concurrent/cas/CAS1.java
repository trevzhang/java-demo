package com.trevzhang.demo.concurrent.cas; /**
 * @author Trevor Zhang
 * @since 2020/9/29 7:43 下午
 */

import java.util.concurrent.atomic.AtomicInteger;

public class CAS1 {

    private static volatile int m = 0;
    private static final AtomicInteger atomicI = new AtomicInteger(0);

    public CAS1() {
    }

    public static void increase1() {
        ++m;
    }

    public static void increase2() {
        atomicI.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] t = new Thread[20];

        for (int i = 0; i < 20; ++i) {
            t[i] = new Thread(() -> {
                increase1();
            });
            t[i].start();
            t[i].join();
        }

        System.out.println(m);
        Thread[] tf = new Thread[20];

        for (int i = 0; i < 20; ++i) {
            tf[i] = new Thread(() -> {
                increase2();
            });
            tf[i].start();
            tf[i].join();
        }

        System.out.println("atomic:" + atomicI.get());
    }
}