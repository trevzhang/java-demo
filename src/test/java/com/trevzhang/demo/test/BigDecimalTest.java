package com.trevzhang.demo.test;

import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.Test;

/**
 * 如何做除法并四舍五入保留2位小数
 *
 * @author Trevor Zhang
 * @since 2020/12/8
 */
public class BigDecimalTest {

    /**
     * 测试舍入
     */
    @Test
    public void testRounding() {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("4");

        BigDecimal result = a.divide(b, 2, BigDecimal.ROUND_HALF_UP);

        System.out.println("result: " + result);
    }

    /**
     * 测试整除求余
     */
    @Test
    public void testDivideAndRemainder() {
        BigDecimal p = new BigDecimal("4");
        BigDecimal q = new BigDecimal("3");

        BigDecimal[] result = p.divideAndRemainder(q);

        System.out.println("result: " + Arrays.toString(result));
    }
}
