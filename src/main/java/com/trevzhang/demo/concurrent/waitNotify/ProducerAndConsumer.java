package com.trevzhang.demo.concurrent.waitNotify;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生产者与消费者问题：wait、notify（notifyAll）应用 一个篮子可存20颗鸡蛋，鸡蛋数量小于5时生产者开始生产鸡蛋,篮子满后生产者停止生产并进入等待状态，鸡蛋数量等于0时消费者不能消费
 *
 * @author Trevor Zhang
 * @since 2020/12/1
 */
public class ProducerAndConsumer {

    private final int MAX = 50;

    public static void main(String[] args) {
        ProducerAndConsumer pac = new ProducerAndConsumer();

        EggBucket eggBucket = new EggBucket();
        Producer p1 = pac.new Producer(eggBucket);
        Consumer c1 = pac.new Consumer(eggBucket);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(p1);
        executorService.submit(c1);
    }


    /**
     * 生产者
     */
    class Producer implements Runnable {

        EggBucket agg;

        public Producer(EggBucket agg) {
            this.agg = agg;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < MAX; i++) {
                    agg.produce();
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费者
     */
    class Consumer implements Runnable {

        EggBucket agg;

        public Consumer(EggBucket agg) {
            this.agg = agg;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < MAX; i++) {
                    agg.consume();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 鸡蛋篮子
     */
    static class EggBucket {

        int maxSize = 20;//篮子大小
        int num = 0;//鸡蛋数量
        boolean allowProduction = true;//是否开始生产，默认true生产

        public EggBucket() {
        }

        /**
         * 生产方法
         */
        protected synchronized void produce() throws InterruptedException {
            //为了避免虚假唤醒，需要不断的检测满足条件
            while (!allowProduction) {
                this.wait();
            }
            System.out.println("生产了一颗鸡蛋，现有鸡蛋：" + (++num) + "颗");
            if (num >= maxSize) {
                allowProduction = false;
                System.out.println("篮子满了，生产者停止工作！");
            }
            this.notifyAll();
        }

        /**
         * 消费方法
         */
        protected synchronized void consume() throws InterruptedException {
            while (num == 0) {
                System.out.println("现有鸡蛋：" + num + "颗，消费者不可消费！");
                this.wait();
            }
            System.out.println("消费一颗鸡蛋，现有鸡蛋：" + (--num) + "颗");
            if (num <= 5 && !allowProduction) {
                allowProduction = true;
                System.out.println("通知生产者开始工作！");
            }
            this.notifyAll();
        }
    }
}
