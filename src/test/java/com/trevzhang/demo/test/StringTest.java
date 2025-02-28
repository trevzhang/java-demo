package com.trevzhang.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

/**
 * @author Trevor Zhang
 * @since 2020/12/2
 */
public class StringTest {

    @Test
    public void testZHStringSize() {
        String zh = "你好哈哈哈";
        // 5
        System.out.println(zh.length());
    }

    @Test
    public void testListToString() {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strList.add("测试" + i);
        }
        System.out.println(String.valueOf(strList));
    }

    @Test
    public void testStringArray() {
        String originSellRanges = null;
        String[] sellRanges = Optional.ofNullable(originSellRanges).orElse("").split(",");
        List<String> sellRangeList = Arrays.asList(sellRanges);
        System.out.println(sellRangeList);
    }

    @Test
    public void test01() {
        String str = "abc";
        String str2 = new String("abc");

        System.out.println("str: " + str);
        System.out.println("str2: " + str2);
        System.out.println(str.equals(str2));
        System.out.println(str == str2);
    }

    @Test
    public void test02() {
        String a = "abcdefg";
        System.out.println(a.charAt(1));
    }

    @Test
    public void test03() {
        String str = null;
        String str1 = "参数为空：";
        System.out.println(str1 + str);
    }

    @Test
    public void test04() {
        String orderNo="123";
        Integer gameBaseId = 321;
        String formatted = String.format("http://example.com/pay_detail?order_no=%s&game_base_id=%s", orderNo, gameBaseId);
        System.out.println(formatted);
    }
}
