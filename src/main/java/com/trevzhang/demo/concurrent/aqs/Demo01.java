package com.trevzhang.demo.concurrent.aqs;

import java.util.concurrent.TimeUnit;

public class Demo01 {

    private MyLock lock = new MyLock();
    private int m = 0;

    public int next() {
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(2);
            return m++;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo01 demo = new Demo01();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            th[i].start();
        }
    }
}
