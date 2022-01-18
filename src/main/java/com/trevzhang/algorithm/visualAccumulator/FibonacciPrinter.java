package com.trevzhang.algorithm.visualAccumulator;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 斐波那契数列，用来演示闭包
 *
 * @author zhangchunguang.zcg
 * @since 2022/1/18 5:59 下午
 */
public class FibonacciPrinter {

    @FunctionalInterface
    interface fun {
        int f();
    }

    @Setter
    @Getter
    @AllArgsConstructor
    static class MyInter {
        Integer i;
    }

    public static void main(String[] args) {
        print(10);
    }

    public static void print(int n) {
        MyInter last = new MyInter(0);
        MyInter next = new MyInter(1);
        fun fun = () -> {
            int r = next.getI();
            int i = last.getI() + next.getI();
            last.setI(next.getI());
            next.setI(i);
            return r;
        };
        for (int i = 0; i < n; i++) {
            System.out.println(fun.f());
        }
    }

}
