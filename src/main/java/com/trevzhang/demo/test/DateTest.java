package com.trevzhang.demo.test;

import java.util.Date;

/**
 * @author 春火
 * @since 2021/1/28 1:57 下午
 */
public class DateTest {
    public static void main(String[] args) {
        int i, j;
        i = 3100;
        j = 3300;
        StringBuilder stringBuilder = new StringBuilder();
        for (int k = i; k < j; k++) {
            stringBuilder.append(k).append(",");
        }
        System.out.println(stringBuilder.toString());
    }
}
