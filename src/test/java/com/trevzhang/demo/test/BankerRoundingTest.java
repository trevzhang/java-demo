package com.trevzhang.demo.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 银行家舍入法
 *
 * @author Murakami Haruki
 * @since 2024/2/18 20:09
 */
public class BankerRoundingTest {

    @Test
    public void test1() {
        DecimalFormat df = new DecimalFormat("0.00");

        // from 1.001 to 2.000, step 0.001
        BigDecimal originSum = new BigDecimal("0");
        BigDecimal calSum = new BigDecimal("0");

        for (BigDecimal i = new BigDecimal("1.005");
             i.compareTo(new BigDecimal("2.000")) <= 0;
             i = i.add(new BigDecimal("0.010")))
        {
            String cal = df.format(i);
            originSum = originSum.add(i);
            calSum = calSum.add(new BigDecimal(cal));
            System.out.println(i + ": \t" + cal);
        }

        System.out.println("originSum: " + originSum);
        System.out.println("calSum: " + calSum);
    }

    @Test
    public void test2() {
        // from 1.0015 to 2.000, step 0.0010 or 0.0050 based on condition
        DecimalFormat df = new DecimalFormat("0.00");
    
        BigDecimal originSum = new BigDecimal("0");
        BigDecimal calSum = new BigDecimal("0");
    
        for (BigDecimal i = new BigDecimal("1.0050");
             i.compareTo(new BigDecimal("2.0000")) <= 0;
            )
        {
            String cal = df.format(i);
            originSum = originSum.add(i);
            calSum = calSum.add(new BigDecimal(cal));
            System.out.println(i + ": \t" + cal);
    
            // Condition to adjust the increment of i
            BigDecimal remainder = i.remainder(new BigDecimal("0.005"));
            if (remainder.compareTo(new BigDecimal("0.0009")) < 0) {
                i = i.add(new BigDecimal("0.0001"));
            } else {
                i = i.add(new BigDecimal("0.0091"));
            }
        }
    
        System.out.println("originSum: " + originSum);
        System.out.println("calSum: " + calSum);
    }
}
