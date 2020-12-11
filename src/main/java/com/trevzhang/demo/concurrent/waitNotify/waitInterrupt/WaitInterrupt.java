package com.trevzhang.demo.concurrent.waitNotify.waitInterrupt;

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
}
