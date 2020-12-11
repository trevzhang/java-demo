package com.trevzhang.demo.concurrent.waitNotify.waitInterrupt;

/**
 * 等待线程 作用: 持有一把锁, 调用锁的wait方法进入等待
 */
public class WaitThread extends Thread {

    private Lock lock;

    WaitThread(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.doWait();
    }
}