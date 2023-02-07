package com.trevzhang.demo.test;

import org.junit.Test;

/**
 * @author Trevor Zhang
 * @since 2020/11/25
 */
public class IntegerTest {

    @Test
    public void test1() {
        // 1<<30 是java.util.HashMap的最大容量
        int a = 1 << 30;
        System.out.println("a: " + a);
        System.out.println("int max: " + Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
//        testIntegerCache();
//        testInOutBox();

    }

    //死锁
    //代码来源于《深入理解Java虚拟机》第4章4.3.1 P121。
    public static class SynAddRunnable implements Runnable {

        int a, b;

        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }

        public static void main(String[] args) {
            for (int i = 0; i < 1000; i++) {
                new Thread(new SynAddRunnable(1, 2)).start();
                new Thread(new SynAddRunnable(2, 1)).start();
            }
        }
    }

    //拆装箱
    private static void testInOutBox() {
        //情景4
        int a = 1;
        Integer b = Integer.valueOf(1);
        Integer c = new Integer(1);

        System.out.println(a == b);//true
        System.out.println(a == c);//true
        System.out.println(b == c);//false
    }

    private static void testIntegerCache() {
        //IntegerCache
        //情景1
        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);//false

        //情景2
        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);//true。b.intValue()

        //情景3
        Integer e = new Integer(1);
        Integer f = new Integer(1);
        System.out.println(e == f);//false}
    }

    /**
     * 打印Integer和Long的MAX_VALUE
     */
    @Test
    public void testPrintIntegerMax() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }
}
