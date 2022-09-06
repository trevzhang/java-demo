package com.trevzhang.demo.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
     * 转百分比展示
     *
     * @param value
     * @return
     */
    private static String toRate(BigDecimal value) {
        return value.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP) + "%";
    }
    @Test
    public void testDivide() {
        int a = 1030;
        int b = 1024;
        int result = a % b;
        int res = a / b;
        System.out.println("result: "+result);
        System.out.println("res: "+res);
    }

    @Test
    public void testRate() {
        BigDecimal value = new BigDecimal("0.0972");
        BigDecimal value1 = new BigDecimal("0.9972");
        BigDecimal value2 = new BigDecimal("0.00721");
        System.out.println(toRate(value));
        System.out.println(toRate(value1));
        System.out.println(toRate(value2));
    }

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
