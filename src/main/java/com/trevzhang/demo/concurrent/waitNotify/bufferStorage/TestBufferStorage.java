package com.trevzhang.demo.concurrent.waitNotify.bufferStorage;

/**
 * 测试类
 */
public class TestBufferStorage {

    public static void main(String[] args) {
        BufferStorage bufferStorage = new BufferStorage(10);
        ProducerThread producerThread = new ProducerThread(bufferStorage);
        CustomerThread customerThread = new CustomerThread(bufferStorage);
        producerThread.start();
        customerThread.start();
    }
}