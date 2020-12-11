package com.trevzhang.demo.concurrent.cas; /**
 * @author Trevor Zhang
 * @since 2020/9/29 7:44 下午
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CAS2 {

    private static final AtomicStampedReference asr = new AtomicStampedReference(100, 1);

    public CAS2() {
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger stamp = new AtomicInteger();
        Thread tf1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException var1) {
                var1.printStackTrace();
            }
            System.out.println(asr.compareAndSet(100, 110, asr.getStamp(), asr.getStamp() + 1));
        });
        Thread tf2 = new Thread(() -> {
            stamp.set(asr.getStamp());
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException var1) {
                var1.printStackTrace();
            }

            System.out.println(asr.compareAndSet(110, 100, asr.getStamp(), asr.getStamp() + 1));
        });
        Thread tf3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
            System.out.println(asr.compareAndSet(100, 120, stamp.get(), stamp.get() + 1));
        });
        tf1.start();
        tf2.start();
        tf3.start();
    }
}

