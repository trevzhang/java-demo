package com.trevzhang.demo.test;

import org.junit.Test;

/**
 * @author Haruki
 * @since 2024/9/25 12:01
 */
public class ByteTest {
    @Test
    public void test() {
        // byte range is -128 to 127
        Byte b = new Byte("127");
        System.out.println(String.valueOf(b));

        Byte b2 = new Byte("-128");
        System.out.println(String.valueOf(b2));
    }
}
