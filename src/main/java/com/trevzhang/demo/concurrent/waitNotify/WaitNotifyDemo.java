package com.trevzhang.demo.concurrent.waitNotify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Trevor Zhang
 * @since 2020/12/1
 */
public class WaitNotifyDemo {

    static boolean wait = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {

        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                // 当条件不满足时，继续wait，同时释放了lock的锁
                while (wait) {
                    try {
                        System.out.println(Thread.currentThread()
                            + " wait is true. wait@ "
                            + new SimpleDateFormat("HH:mm:ss")
                            .format(new Date()));
                        lock.wait();
                        System.out.println(Thread.currentThread()
                            + " after wait");
                    } catch (InterruptedException e) {
                    }
                }
                // 条件满足时，完成工作
                System.out.println(Thread.currentThread()
                    + " wait is false. running@ "
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // wait()会立刻释放synchronized（obj）中的obj锁，以便其他线程可以执行obj.notify()
    // 但是notify()不会立刻立刻释放sycronized（obj）中的obj锁，必须要等notify()所在线程执行完synchronized（obj）块中的所有代码才会释放这把锁.
    // yield(),sleep()不会释放锁
    static class Notify implements Runnable {

        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread()
                    + " hold lock. notify @ "
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                wait = false;
                try {
                    Thread.currentThread().sleep(5000l);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            // 睡眠1纳秒, 增加其他线程获取锁的概率
//            try {
//                TimeUnit.NANOSECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread()
                    + " hold lock again. sleep@ "
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    Thread.currentThread().sleep(5000l);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}