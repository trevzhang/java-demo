package com.trevzhang.demo.concurrent.waitNotify.bufferStorage;

/**
 * 建立一个生产者线程
 */
public class ProducerThread extends Thread {

    BufferStorage bufferStorage;

    public ProducerThread(BufferStorage bufferStorage) {
        super();
        this.bufferStorage = bufferStorage;
        super.setName("ProducerThread");
    }

    public void run() {
        for (int i = 0; i < 200; i++) {
            bufferStorage.put("zcg:");
        }
    }
}