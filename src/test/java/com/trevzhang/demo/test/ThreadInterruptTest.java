package com.trevzhang.demo.test;

import org.junit.Test;

/**
 * @author Haruki
 * @since 2024/9/4 11:17
 */
public class ThreadInterruptTest {
    @Test
    public void testInterrupt() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //exception被捕获，但是为输出为false 因为标志位会被清空
                    System.out.println("isInterrupted: " + this.isInterrupted());
                    // 正确处理方式之一：重设中断标志位为true，并让上层代码知道当前线程已经被中断
                    Thread.currentThread().interrupt();
                }
                System.out.println(isInterrupted());//true
            }
        };
        t.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        t.interrupt();//置为true
    }
}
