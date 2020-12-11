package com.trevzhang.demo.concurrent.waitNotify.bufferStorage;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 先建立一个缓冲区的存储
 */
public class BufferStorage {

    private int maxSize = 0;

    private List<String> list;

    public BufferStorage(int maxSize) {
        this.maxSize = maxSize;
        list = new LinkedList<>();
    }

    synchronized public void put(String str) {
        while (list.size() == maxSize) {//当list中装的数据最大时，就等待消费者线程
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(str + ":" + new Date().getTime());
        System.out.printf("put %d item(s).", list.size());
        System.out.println();
        notifyAll();
    }

    synchronized public void take() {
        while (list.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("take %d: %s", list.size(), ((LinkedList<?>) list).poll());  //poll 先得到数据，在remove数据
        System.out.println();
        notifyAll();
    }

}