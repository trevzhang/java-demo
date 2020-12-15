package com.trevzhang.demo.test;

import java.util.HashMap;
import java.util.Map;

/**
 * hashmap可以存放空键
 * @author Trevor Zhang
 * @since 2020/12/15
 */
public class HashMapTest2 {

    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map);
        System.out.println(map.get(null));
    }
}
