package com.trevzhang.demo.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Murakami Haruki
 * @since 2024/2/2 18:25
 */
public class TreeMapTest {

    @Test(expected = ClassCastException.class)
    public void testKeyNotComparable() {
        // 创建一个HashMap，用于存放不可比较的key
        Map<Object, String> hashMap = new HashMap<>();
        hashMap.put(new Object(), "value1");
        hashMap.put(new Object(), "value2");

        // 创建一个TreeMap，并尝试使用putAll方法
        TreeMap<Object, String> treeMap = new TreeMap<>();
        treeMap.putAll(hashMap); // 这里预期会抛出ClassCastException
    }
}