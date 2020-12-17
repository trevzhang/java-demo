package com.trevzhang.demo.test;

import org.junit.Test;

/**
 * @author Trevor Zhang
 * @since 2020/12/17
 */
public class FloatTest {

    /**
     * Double.toString() 并不能保证精度和准确度
     */
    @Test
    public void testToString() {
        float f = 10.0f;
        float q = f / 3;
        System.out.println(Double.toString(q));
    }
}
