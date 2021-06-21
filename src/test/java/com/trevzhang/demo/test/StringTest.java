package com.trevzhang.demo.test;

import org.junit.Test;

/**
 * @author Trevor Zhang
 * @since 2020/12/2
 */
public class StringTest {

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
}
