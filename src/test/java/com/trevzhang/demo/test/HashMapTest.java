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
