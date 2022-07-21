package com.trevzhang.demo.test;

import org.junit.Test;

import java.util.Base64;

/**
 * @author zhangchunguang.zcg
 * @since 2022/7/21 8:35 PM
 */
public class Base64Test {
    @Test
    public void test1() {
        byte[] decodeBytes=Base64.getDecoder().decode("YWJjMTIzNDU2Nw==");
        String decodeStr = new String(decodeBytes);
        System.out.println(decodeStr);
    }
}
