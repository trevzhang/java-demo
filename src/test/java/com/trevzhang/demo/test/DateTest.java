package com.trevzhang.demo.test;

import java.util.Date;
import org.junit.Test;

/**
 * 日期测试
 *
 * @author 春火
 * @since 2021/1/28 1:57 下午
 */
public class DateTest {

    @Test
    public void testStringBuilder() {
        Date testDate = new Date(116, 3, 5);
        System.out.println(testDate);
    }
}
