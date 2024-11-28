package com.trevzhang.demo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Haruki
 * @since 2024/11/28 16:39
 */
public class ConditionTest {
    private static final Logger logger = LoggerFactory.getLogger(ConditionTest.class);

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int count = 0;
    private final int LIMIT = 5;

    // 生产方法
    public void produce() {
        lock.lock();
        try {
            while (count >= LIMIT) {
                logger.info("{} -> 仓库已满，等待消费...", Thread.currentThread().getName());
                condition.await();
            }
            count++;
            logger.info("{} -> 生产了一个产品，当前产品数：{}", Thread.currentThread().getName(), count);
            condition.signalAll();
        } catch (InterruptedException e) {
            logger.error("生产过程中断异常", e);
        } finally {
            lock.unlock();
        }
    }

    // 消费方法
    public void consume() {
        lock.lock();
        try {
            while (count <= 0) {
                logger.info("{} -> 仓库为空，等待生产...", Thread.currentThread().getName());
                condition.await();
            }
            count--;
            logger.info("{} -> 消费了一个产品，当前产品数：{}", Thread.currentThread().getName(), count);
            condition.signalAll();
        } catch (InterruptedException e) {
            logger.error("消费过程中断异常", e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();

        // 创建3个生产者线程
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    test.produce();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        logger.error("生产者睡眠被中断", e);
                    }
                }
            }, "生产者-" + i).start();
        }

        // 创建2个消费者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 7; j++) {
                    test.consume();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.error("消费者睡眠被中断", e);
                    }
                }
            }, "消费者-" + i).start();
        }
    }
}
