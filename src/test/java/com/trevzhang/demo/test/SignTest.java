package com.trevzhang.demo.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author Haruki
 * @since 2024/11/4 15:40
 */
public class SignTest {
    @Test
    public void test01() {
        String result = DigestUtils.md5Hex("aaaa373412745138542234202cb962ac59075b964b07152d234b70");
        System.out.println(result);
    }
}
