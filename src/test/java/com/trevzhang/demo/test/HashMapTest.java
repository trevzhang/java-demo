package com.trevzhang.demo.test;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.Test;

public class HashMapTest {

    Map<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
    Map<Object, Object> hashMap = new HashMap<>();
    Map<Object, Object> hashTable = new Hashtable<>();
    static final int MAXIMUM_CAPACITY = 1 << 30;

    @Test
    public void testTableSizeFor() {
        System.out.println(tableSizeFor(2));
        System.out.println(tableSizeFor(4));
        System.out.println(tableSizeFor(8));
        System.out.println(tableSizeFor(16));
        System.out.println(tableSizeFor(20));
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public void test02() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "null");
        map.put("null", null);
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void test01() {
        Object o = new AtomicLong(1);
        int hashCode = o.hashCode();
        int hash = hash(o);
        System.out.println("hashCode():");
        System.out.println(Integer.toBinaryString(hashCode));
        System.out.println("hash:");
        System.out.println(Integer.toBinaryString(hash));
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
