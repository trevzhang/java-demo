package com.trevzhang.demo.test;

import java.text.DecimalFormat;
import java.util.UUID;

/**
 * @author 春火
 * @since 2021/3/4 2:24 下午
 */
public class UUIDTest {
    private static final String STR_FORMAT = "000000000000";
    public static String haoAddOne(String liuShuiHao){
        Integer intHao = Integer.parseInt(liuShuiHao);
        intHao++;
        DecimalFormat df = new DecimalFormat(STR_FORMAT);
        return df.format(intHao);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(haoAddOne(String.valueOf(i)));
        }
    }
}
