package com.trevzhang.demo.test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用Thread Local，且remove
 *
 * @author 春火
 * @since 2021/7/3 11:31 下午
 */
public class ThreadLocalTest2 {

    public static final Integer SIZE = 5;
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
        5, 5, 1,
        TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    static class LocalVariable {//总共有5M
        private byte[] data = new byte[1024 * 1024 * 5];
    }

    static ThreadLocal<LocalVariable> local = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            executor.execute(() -> {
                local.set(new LocalVariable());
                System.out.println("开始执行");
                local.remove();//这句话很关键！
            });
        }
    }
}



