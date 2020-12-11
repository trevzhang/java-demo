package com.trevzhang.demo.concurrent.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Trevor Zhang
 * @since 2020/11/30
 */
public class WorkPlace {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Resource shareResource = new Resource();
        Thread worker1 = new Thread(new ContendWorker(shareResource, lock));
        Thread worker2 = new Thread(new ContendWorker(shareResource, lock));

        worker1.setName("Arthur");
        worker2.setName("John");

        worker1.start();
        worker2.start();

    }
}
