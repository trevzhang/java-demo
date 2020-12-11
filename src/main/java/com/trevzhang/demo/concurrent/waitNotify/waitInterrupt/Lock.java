package com.trevzhang.demo.concurrent.waitNotify.waitInterrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Trevor Zhang
 * @since 2020/12/4
 */
public class Lock {

    public synchronized void doWait() {
        try {
            System.out.println(Thread.currentThread().getName() + ": 锁等待...");
            wait();
            System.out.println(Thread.currentThread().getName() + ": 锁苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void doSomething() {
        System.out.println(Thread.currentThread().getName() + ": do something..., 并抢到了锁");
        int i = 1;
        while (i <= 4) {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + ": do something, " + i * 25 + "% finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
