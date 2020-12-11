package com.trevzhang.demo.concurrent.waitNotify;

import java.util.concurrent.TimeUnit;

/**
 * 作用: 验证调用interrupt()方法, wait的线程要抛异常也要等待获取锁才行
 * @author Trevor Zhang
 * @since 2020/12/1
 */
public class WaitInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new Lock();
        //启用新线程, 使lock进入等待
        Thread waitThread = new WaitThread(lock);
        waitThread.start();

        System.out.println(Thread.currentThread().getName() + ": 睡眠1秒...");
        TimeUnit.SECONDS.sleep(1);

        //调用锁的另一个同步方法,抢占锁
        new Thread(lock::doSomething).start();
        TimeUnit.SECONDS.sleep(1);

        //在main线程调用lock的中断
        System.out.println(waitThread.getName() + ": 调用中断");
        waitThread.interrupt();
    }

    public static class Lock {

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

    /**
     * 等待线程 作用: 持有一把锁, 调用锁的wait方法进入等待
     */
    public static class WaitThread extends Thread {

        private Lock lock;

        WaitThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.doWait();
        }
    }

}
