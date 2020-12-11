package com.trevzhang.demo.test;

import java.math.BigDecimal;

/**
 * 如何做除法并四舍五入保留2位小数
 * @author Trevor Zhang
 * @since 2020/12/8
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("4");

        BigDecimal result = a.divide(b, 2, BigDecimal.ROUND_HALF_UP);

        System.out.println(result);

    }
}
