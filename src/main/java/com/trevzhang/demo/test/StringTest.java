package com.trevzhang.demo.test;

/**
 * @author Trevor Zhang
 * @since 2020/12/2
 */
public class StringTest {

    public static void main(String[] args) {
        String str = "abc";
        String str2 = new String("abc");

        System.out.println("str: " + str);
        System.out.println("str2: " + str2);
        System.out.println(str.equals(str2));
        System.out.println(str == str2);
    }

}
