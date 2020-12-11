package com.trevzhang.demo.concurrent.waitNotify.bufferStorage;

/**
 * 建立一个消费者线程
 */
public class CustomerThread extends Thread {

    BufferStorage bufferStorage;

    public CustomerThread(BufferStorage bufferStorage) {
        super();
        this.bufferStorage = bufferStorage;
        super.setName("CustomerThread");
    }

    public void run() {
        for (int i = 0; i < 200; i++) {
            bufferStorage.take();
        }
    }
}
