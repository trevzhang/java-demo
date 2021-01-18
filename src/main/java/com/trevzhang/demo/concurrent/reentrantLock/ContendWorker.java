package com.trevzhang.demo.concurrent.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Trevor Zhang
 * @since 2020/11/30
 */
public class ContendWorker implements Runnable {

    Resource resource;

    ReentrantLock lock;

    public ContendWorker(Resource resource, ReentrantLock lock) {
        this.resource = resource;
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": trying lock...");
        System.out.println(Thread.currentThread().getName() + ": locked!");
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + ": releasing lock...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
